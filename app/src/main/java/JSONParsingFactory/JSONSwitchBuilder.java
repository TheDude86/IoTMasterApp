package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import uno.lets.iotmasterapp.Calls;
import uno.lets.iotmasterapp.MainActivity;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONSwitchBuilder extends JSONButtonBuilder {
    public JSONSwitchBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {
        mJSON = json;

        Switch s = new Switch(mActivity);

        if (has("Checked"))
            s.setChecked(json.getBoolean("Checked"));

        if (has("Actions")) {
            final JSONArray actions = json.getJSONArray("Actions");

            s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    try {
                        for (JSONObject j: sortActions(actions)) {

                            switch (j.getString("Type")) {
                                case "Edit":

                                    editValue(b, String.format(j.getString("Key"), Integer.toString(mRow)));

                                    break;
                                case "Update":

                                    if (j.has("Row"))
                                        updateValue(b, j, String.format(j.getString("Row"), Integer.toString(mRow)));
                                    else
                                        updateValue(b, j);

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


                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

        super.Build(s, json);
    }
}
