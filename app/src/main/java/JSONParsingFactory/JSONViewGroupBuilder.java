package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONViewGroupBuilder extends JSONBuilder {

    public JSONViewGroupBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {

    }

    @Override
    public void Build(View v, JSONObject json) throws JSONException {
        mJSON = json;

        ViewGroup g = (ViewGroup) v;

        g.setAlpha(has("Alpha") ? (float) json.getDouble("Alpha") : 1f);


        if (json.has("ID"))
            g.setId(json.getInt("ID"));

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (mContainer instanceof RelativeLayout)
            params = ViewParameters.AddRLParameters(g, json);

        JSONBuilderFactory f = new JSONBuilderFactory(mActivity, g);
        JSONArray children = json.getJSONArray("Children");
        JSONBuilder builder;

        for (int i = 0; i < children.length(); i++) {
            JSONObject j = children.getJSONObject(i);

            builder = f.getBuilder(j);
            builder.mRow = mRow;
            builder.Build(j);

            for (Integer key : builder.mViews.keySet()) {
                mViews.put(key, builder.mViews.get(key));

            }




        }

        mViews.put(v.getId(), v);

        mContainer.addView(g, params);

    }
}
