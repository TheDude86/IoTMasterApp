package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONRelativeLayoutBuilder extends JSONViewGroupBuilder {
    public JSONRelativeLayoutBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {

        RelativeLayout r = new RelativeLayout(mActivity);

        super.Build(r, json);
    }
}
