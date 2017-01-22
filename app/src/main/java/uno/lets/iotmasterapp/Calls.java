package uno.lets.iotmasterapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageButton;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jnovosel on 6/24/16.
 */
public class Calls {
    /**
     * This class doesn't have any constructors and only has static methods.  It is used to shorten
     * code in other classes and also place all of the URLs in the same place in the code so if any
     * links get updated then it is easy to update the client.
     */



    protected static final String BASE_URL = "https://iotgadget-156406.appspot.com/";
//    protected static final String BASE_URL = "http://sample-env.7mex42vnbd.us-west-2.elasticbeanstalk.com/";



    protected static final String NOTIFY_URL = "http://lets-app-server.appspot.com/";
    protected static final String RespondToGroupInvite = "user/respondToGroupInvite";
    protected static final String TransferOwnership = "group/transferOwnership";
    protected static final String SendFriendRequest = "user/sendFriendRequest";
    protected static final String GetNotifications = "user/getNotifications";
    protected static final String InviteGroupToEvent = "event/inviteGroup";
    protected static final String GetCloseEvents = "event/getCloseEvents";
    protected static final String GetProfileByID = "user/getProfileById";
    protected static final String GetGroupComments = "group/getComments";
    protected static final String InviteUserToEvent = "event/inviteUser";
    protected static final String GetAdminGroups = "user/getAdminGroups";
    protected static final String AddGroupComment = "group/addComment";
    protected static final String GroupRemoveUser = "group/removeUser";
    protected static final String GroupInviteUser = "group/inviteUser";
    protected static final String EventAddComment = "event/addComment";
    protected static final String RemoveCohost = "event/removeCohost";
    protected static final String GetEventById = "event/getEventById";
    protected static final String GetMyProfile = "user/getMyProfile";
    protected static final String RemoveFriend = "user/removeFriend";
    protected static final String RemoveAdmin = "group/removeAdmin";
    protected static final String GetAttended = "user/getAttended";
    protected static final String LoginSecure = "user/loginSecure";
    protected static final String EditProfile = "user/editProfile";
    protected static final String UnattendEvent = "event/unattend";
    protected static final String RegisterToken = "push/register";
    protected static final String GetGroupInfo = "group/getInfo";
    protected static final String GetFriends = "user/getFriends";
    protected static final String AddCohost = "event/addCohost";
    protected static final String GetGroups = "user/getGroups";
    protected static final String CreateGroup = "group/create";
    protected static final String CreateEvent = "event/create";
    protected static final String GroupDelete = "group/delete";
    protected static final String AddAdmin = "group/addAdmin";
    protected static final String RemoveToken = "push/remove";
    protected static final String LeaveGroup = "group/leave";
    protected static final String SearchName = "search/name";
    protected static final String CreateUser = "user/create";
    protected static final String EditGroup = "group/edit";
    protected static final String EnterCode = "tools/registerEventCode";
    protected static final String DeleteEvent = "event/delete";



    protected static final String FCMFriendRequest = "friendRequest";
    protected static final String FCMEventInvite = "eventInvite";
    protected static final String FCMAddedComment = "addedComment";
    protected static final String FCMGroupInvite = "groupInvite";
    protected static final String FCMGroupCommentAdded = "groupCommentAdded";
    protected static final String FCMGroupInvitedToEvent = "groupInvited";



    public Calls() {

    }

    protected static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Base method for making network calls.
     *
     * @param url             the extention to be placed on the end of the base URL
     * @param params          paramteters added to the post requests
     * @param responseHandler code to be executed for specific events during the call
     */
    private static void post(String url, RequestParams params,
                             AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Base method for making network calls.
     *
     * @param url             the extention to be placed on the end of the base URL
     * @param params          paramteters added to the post requests
     * @param responseHandler code to be executed for specific events during the call
     */
    private static void postFCM(String url, RequestParams params,
                             AsyncHttpResponseHandler responseHandler) {
        client.post(getNotifyAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * build the full URL from the extention.
     *
     * @param relativeUrl
     * @return
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }


    /**
     * build the full URL from the extention.
     *
     * @param relativeUrl
     * @return
     */
    private static String getNotifyAbsoluteUrl(String relativeUrl) {
        return NOTIFY_URL + relativeUrl;
    }


    public static void getInfo(JsonHttpResponseHandler jsonHttpResponseHandler) {

        client.removeAllHeaders();
        RequestParams params = new RequestParams();

        post("ToFrontEnd", params, jsonHttpResponseHandler);

    }

    public static void send(JSONObject j, JsonHttpResponseHandler jsonHttpResponseHandler) {

        client.removeAllHeaders();
        RequestParams params = new RequestParams();

        Iterator<String> i = j.keys();

        while (i.hasNext()) {
            String s = i.next();

            Log.println(Log.ASSERT, "Tag", s);


            try {
                params.put(s, j.get(s));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        post("Update", params, jsonHttpResponseHandler);

    }



















}
