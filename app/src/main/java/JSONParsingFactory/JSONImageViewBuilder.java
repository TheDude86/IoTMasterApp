package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONImageViewBuilder extends JSONViewBuilder {
    public JSONImageViewBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }


    @Override
    public void Build(JSONObject json) throws JSONException {
        mJSON = json;

        ImageView i = new ImageView(mActivity);
        Picasso.with(mActivity).load(json.getString("Source")).into(i);


        super.Build(i, json);
    }
}
