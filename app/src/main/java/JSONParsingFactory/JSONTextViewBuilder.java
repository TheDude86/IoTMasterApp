package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONTextViewBuilder extends JSONViewBuilder {

    public JSONTextViewBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {
        mJSON = json;

        TextView t = new TextView(mActivity);

        t.setText(String.format(json.getString("Name"), Integer.toString(mRow + 1)));

        if (has("Size"))
            t.setTextSize(json.getInt("Size"));


        super.Build(t, json);
    }

    @Override
    public void Build(View v, JSONObject json) throws JSONException {

        TextView textView = (TextView) v;

        if (v instanceof TextView) {
            textView.setText(String.format(json.getString("Name"), Integer.toString(mRow)));

            if (json.has("Size"))
                textView.setTextSize(json.getInt("Size"));

        }



        super.Build(textView, json);
    }


}
