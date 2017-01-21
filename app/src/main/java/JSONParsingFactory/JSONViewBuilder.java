package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import uno.lets.iotmasterapp.MainActivity;

/**
 * Created by novosejr on 1/20/2017.
 */

public class JSONViewBuilder extends JSONBuilder {


    public JSONViewBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {

    }

    @Override
    public void Build(View v, JSONObject json) throws JSONException {
        mJSON = json;

        v.setAlpha(has("Alpha") ? (float) json.getDouble("Alpha") : 1f);


        if (json.has("ID")) {
            v.setId(json.getInt("ID"));
            ((MainActivity) mActivity).mViews.put(json.getInt("ID"), v);

        }

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mContainer instanceof RelativeLayout)
            params = ViewParameters.AddRLParameters(v, json);

        mViews.put(v.getId(), v);

        mContainer.addView(v, params);

    }


}
