package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONCardViewBuilder extends JSONViewGroupBuilder {
    public JSONCardViewBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {

        CardView c = new CardView(mActivity);


        super.Build(c, json);
    }
}
