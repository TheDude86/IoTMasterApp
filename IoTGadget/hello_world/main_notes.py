# Copyright 2016 Google Inc.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# [START app]
import logging
import requests
import json
from flask import Flask, request, url_for, jsonify


app = Flask(__name__)


@app.route('/')
def hello():
    return 'Hello World!'

# [START submitted]
@app.route('/submitted', methods=['POST'])
def submitted_form():
    name = request.form['name']
    email = request.form['email']

    data = {}
    data['name'] = name
    data['email'] = email
    return jsonify(**data) 


@app.route('/friendRequest', methods=['POST'])
def friendRequest():
    sender = request.form['sender']
    reciever = request.form['reciever']

    r = requests.post('http://letsapi.azurewebsites.net/user/getFriends', data = {'user_id':reciever})
    array = json.loads(r.text)

    for j in array:
        if (str(sender) == str(j['sender'])) & (str(j['status']) == 'False'):
            sendNotification(reciever, "New Friend Request", "You have a new friend request!")

    
    data = {'message':'complete'}
    return jsonify(**data)



# Need a new way to get event info even if the event is private
@app.route('/eventInvite', methods=['POST'])
def eventInvite():
    sender = request.form['sender']
    reciever = request.form['reciever']
    event = request.form['event']

    r = requests.post('http://letsapi.azurewebsites.net/event/getEventById', data = {'event_id':event})
    title = json.loads(r.text)['Event_info'][0]['Event_Name']
    array = json.loads(r.text)['Attending_users']

    for s in array:
        if str(s['user_id']) == str(sender):
            for j in array:
                if (str(j['user_id']) == str(reciever)) & (str(j['status']) == 'False'):
                    sendNotification(j['user_id'], 'You\'ve been invited!', '%s has invited you to the event %s' % (s['name'], title)) 
    
    data = {'message':'complete'}
    return jsonify(**data)



# Need a new way to get event info even if the event is private
@app.route('/addedComment', methods=['POST'])
def addedComment():
    sender = request.form['sender']
    event = request.form['event']

    r = requests.post('http://letsapi.azurewebsites.net/event/getEventById', data = {'event_id':event})
    title = json.loads(r.text)['Event_info'][0]['Event_Name']
    array = json.loads(r.text)['Attending_users']
    comments = json.loads(r.text)['Comments']
    
    for c in comments:
        if str(c['user_id']) == str(sender):
            for j in array:
                if (str(j['user_id']) != str(sender)) & (str(j['status']) == 'True'):
                    sendNotification(j['user_id'], "New Comment", "Somebody commented in %s" % title)
            break
    
    data = {'message':'complete'}
    return jsonify(**data)



# Need a new way to get group info even if the group is private
@app.route('/groupInvite', methods=['POST'])
def groupInvite():
    sender = request.form['sender']
    reciever = request.form['reciever']
    group = request.form['group']

    r = requests.post('http://letsapi.azurewebsites.net/group/getInfo', data = {'group_id':group})
    title = json.loads(r.text)['Group_info'][0]['group_name']
    array = json.loads(r.text)['Group_users']
    
    for s in array:
        if (str(s['user_id']) == str(sender)):
            for j in array: 
                if (str(j['user_id']) == str(reciever)) & (str(j['status']) == 'False'):
                    sendNotification(reciever, 'You\'ve been invited!', '%s has invited you to the group %s' % (s['name'], title))
                    
            break
            
    
    data = {'message':'complete'}
    return jsonify(**data)




# Need a new way to get group info even if the group is private
@app.route('/groupCommentAdded', methods=['POST'])
def groupCommentAdded():
    commenter = request.form['sender']
    group = request.form['group']

    r = requests.post('http://letsapi.azurewebsites.net/group/getInfo', data = {'group_id':group})
    r2 = requests.post('http://letsapi.azurewebsites.net/group/getComments', data = {'group_id':group})
    title = json.loads(r.text)['Group_info'][0]['group_name']
    users = json.loads(r.text)['Group_users']
    comments = json.loads(r2.text)
    
    for c in comments:
        if str(c['user_id']) == str(commenter):
            for j in users:
                if (str(j['user_id']) != str(commenter)) & (str(j['status']) == 'True'):
                    sendNotification(commenter, 'New Comment', '%s has posted a comment to the group %s' % (c['name'], title))
            break
                    
    data = {'message':'complete'}
    return jsonify(**data)



@app.route('/groupInvited', methods=['POST'])
def groupInvited():
    sender = request.form['sender']
    groupID = request.form['group']
    eventID = request.form['event']

    groupResponse = requests.post('http://letsapi.azurewebsites.net/group/getInfo', data = {'group_id':groupID})
    eventResponse = requests.post('http://letsapi.azurewebsites.net/event/getEventById', data = {'event_id':eventID})
    admins = json.loads(groupResponse.text)['Group_admins']
    members = json.loads(groupResponse.text)['Group_users']
    groupTitle = json.loads(groupResponse.text)['Group_info'][0]['group_name']
    eventTitle = json.loads(eventResponse.text)['Event_info'][0]['Event_Name']
    
    for a in admins:
        if str(a['user_id']) == str(sender):
            for m in members:
                if (str(m['user_id']) != str(a['user_id'])) & (str(m['status']) == 'True'):
                    sendNotification(m['user_id'], "You\'re group's been invited!", "%s has invited your group, %s, to %s" % (a['name'], groupTitle, eventTitle))
    
        

    data = {'message':'complete'}
    return jsonify(**data)


# Update when Bill fixes server issue
# -Add call to get user's tokens from server
# -loop through all returned active tokens and send notification
def sendNotification(userID, title, message):
    
    userResponse = requests.post('http://letsapi.azurewebsites.net/push/getUserTokensAsGod', data = {'user_id':userID, 'password' : 'q8h:FKvYjRDQaBccWzfIE4Y=9DLsICReHp8A09jm6CWheI/X4LZ4p5P0DmX:bWC/kYygyf82UCxG9j-BIbaExs2+Fmm5m3hux8n;QSvqh6hyvj-9S5exiY=0jMGi2m?r+Yk:OOfAP7jc'})

    tokens = json.loads(userResponse.text)
    
    for token in tokens:
        headers = {'Authorization':'key=AIzaSyBk1fYCEXPxHTmj5G1ESrG7YHhQU3hm7qI',
                   'Content-Type':'application/json'}
        
        if str(token['active']) == 'True':
            data = '{\"to\":\"%s\",\"priority\":10,\"notification\":{\"title\":\"%s\",\"body\":\"%s\",\"badge\":\"1\"}}' % (token['fire_token'], title, message)
            requests.post('http://fcm.googleapis.com/fcm/send', data=data, headers=headers)
    
    

@app.errorhandler(500)
def server_error(e):
    # Log the error and stacktrace.
    logging.exception('An error occurred during a request.')
    return 'An internal error occurred.', 500
# [END app]


