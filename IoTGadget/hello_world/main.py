
import logging
import requests
import json
import datetime
from flask import Flask, jsonify, request, json

app = Flask(__name__)
data = []
time = 0


@app.route("/")
def main():
    return 'Hello World!'

@app.route("/ToFrontEnd", methods=['POST'])
def frontEnd():
    global data
    frontData = {}

    for j in data:
        frontData[j['Name']] = j['On']
        frontData[j['Name'] + ' Color'] = j['Color']

    interface = {"Type":"RecyclerView","Width":-1,"Height":-1,"Margin Left":0,"Margin Right":0,"Margin Top":0,"Align Parent Top":True,"Align Parent Left":True,"Alpha":1,"Count":len(data),"ID":2,"Adapter":{"Type":"CardView","Width":-1,"Height":-2,"Margin Left":30,"Margin Right":30,"Margin Bottom":40,"Align Parent Top":True,"Align Parent Left":True,"Alpha":1,"Count":0,"ID":0,"Children":[{"Type":"RelativeLayout","Width":-1,"Height":-2,"Margin Left":0,"Margin Right":0,"Margin Top":0,"Align Parent Top":True,"Align Parent Left":True,"Alpha":1,"ID":1,"Children":[{"Type":"Switch","Name":"Light Switch","Width":-2,"Height":-2,"Margin Left":50,"Margin Right":0,"Margin Top":50,"Margin Bottom":50,"Align Parent Top":True,"Align Parent Left":True,"Alpha":1,"ID":5,"Actions":[{"Type":"Edit","Key":"Light %s","Order":0},{"Type":"Update","Row":"%s","Row ID":6,"ID":2,"True Text":"On","False Text":"Off","Text":"Light","Order":1},{"Type": "Send", "Order":2}]},{"Type":"TextView","Name":"Text","Width":-2,"Height":-2,"Margin Left":50,"Margin Right":100,"Margin Top":100,"Margin Bottom":50,"Align Parent Top":True,"Align Parent Right":True,"Alpha":1,"ID":6},{"Type":"TextView","Name":"Light %s:","Width":-2,"Height":-2,"Margin Left":50,"Margin Right":250,"Margin Top":100,"Margin Bottom":50,"Align Parent Top":True,"Align Parent Right":True,"Alpha":1,"ID":5},{"Type":"Button","Name":"  Set Light Color  ","Width":-2,"Height":-2,"Margin Left":50,"Margin Right":0,"Margin Top":200,"Margin Bottom":50,"Align Parent Top":True,"Align Parent Left":True,"Alpha":1,"ID":3,"Actions":[{"Type":"Layout","Layout":"Dialog Color","Order":0},{"Type":"Update","Row":"%s","Row ID":3,"ID":2,"Background":"%s","Order":1},{"Type":"Edit","Key":"Light %s Color","Order":2},{"Type": "Send", "Order":3}]}]}]}}
    
    send = {}
    send['Data'] = frontData
    send['Interface'] = interface

    return jsonify(**send)


@app.route("/Update", methods=['POST'])
def update():
    global data
    input = []
    
    for i in range(len(data)):
        title = 'Light ' + str(i)
        j = {}
        j['Name'] = title
        j['On'] = request.form[title]
        j['Color'] = request.form[title + ' Color']
        input.insert(0, j)
        
    data = input

    
    return jsonify(result = data)



@app.route("/AddLight", methods=['POST'])
def add():
    global data
    data.insert(0, {'Name': 'Light ' + str(len(data)), 'On': False, 'Color':-1})

    return jsonify(results=data)



@app.route("/Start", methods=['POST'])
def start():
    global time
    time = datetime.datetime.now()

    return jsonify(results={'message': time + datetime.timedelta(0, 120)})



@app.route("/Reset", methods=['POST'])
def reset():
    global data
    data = [{'Name': 'Light 0', 'On': False, 'Color':-1},
            {'Name': 'Light 1', 'On': False, 'Color':-1},
            {'Name': 'Light 2', 'On': False, 'Color':-1}]

    return jsonify(results=data)


if __name__ == "__main__":
    global data
    data = [{'Name': 'Light 0', 'On': False, 'Color':-1},
            {'Name': 'Light 1', 'On': False, 'Color':-1},
            {'Name': 'Light 2', 'On': False, 'Color':-1}]
    app.run()

