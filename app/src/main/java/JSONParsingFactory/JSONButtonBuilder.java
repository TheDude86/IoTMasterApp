package JSONParsingFactory;


import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import uno.lets.iotmasterapp.Calls;
import uno.lets.iotmasterapp.MainActivity;

/**
 * Created by novosejr on 1/20/2017.
 */

public class JSONButtonBuilder extends JSONTextViewBuilder{

    public JSONButtonBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }


    @SuppressWarnings("ResourceType")
    @Override
    public void Build(JSONObject json) throws JSONException {
        mJSON = json;

        Button b = new Button(mActivity);
        b.setText(json.getString("Name"));

        if (has("Size"))
            b.setTextSize(json.getInt("Size"));

        if (has("Actions")) {
            final JSONArray actions = json.getJSONArray("Actions");

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        for (JSONObject j: sortActions(actions)) {

                            boolean b = false;

                            switch (j.getString("Type")) {
                                case "Edit":

                                    editValue(true, String.format(j.getString("Key"), Integer.toString(mRow)));

                                    break;
                                case "Update":

                                    if (j.has("Row"))
                                        updateValue(true, j, String.format(j.getString("Row"), Integer.toString(mRow)));
                                    else
                                        updateValue(true, j);

                                    break;
                                case "Layout":
                                    ArrayList<JSONObject> remaining = sortActions(actions);
                                    remaining.remove(j);

                                    editLayout(j.getString("Layout"), remaining);

                                    b = true;
                                    break;

                                case "Send":

                                    Calls.send(((MainActivity) mActivity).mData, new JsonHttpResponseHandler() {
                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            Log.println(Log.ASSERT, "TEST", response.toString());
                                        }
                                    });

                                    break;

                            }

                            if (b)
                                break;

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

        super.Build(b, json);


    }

    @Override
    public void Build(View v, JSONObject json) throws JSONException {
        mJSON = json;

        Button b = (Button) v;

        b.setText(json.getString("Name"));

        if (has("Size"))
            b.setTextSize(json.getInt("Size"));

        super.Build(b, json);

    }


}
