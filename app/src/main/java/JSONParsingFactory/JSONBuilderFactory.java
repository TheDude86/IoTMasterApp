package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONBuilderFactory {
    AppCompatActivity mActivity;
    ViewGroup mContainer;

    public JSONBuilderFactory(AppCompatActivity a, ViewGroup c) {
        mActivity = a;
        mContainer = c;
    }


    public JSONBuilder getBuilder(JSONObject j) throws JSONException {
        JSONBuilder b = new JSONViewBuilder(mActivity, mContainer);

        String s = j.getString("Type");

        switch (s) {
            case "Button":
                b = new JSONButtonBuilder(mActivity, mContainer);

                break;
            case "Switch":
                b = new JSONSwitchBuilder(mActivity, mContainer);

                break;
            case "TextView":
                b = new JSONTextViewBuilder(mActivity, mContainer);

                break;
            case "ImageView":
                b = new JSONImageViewBuilder(mActivity, mContainer);

                break;
            case "CardView":
                b = new JSONCardViewBuilder(mActivity, mContainer);

                break;
            case "RelativeLayout":
                b = new JSONRelativeLayoutBuilder(mActivity, mContainer);

                break;
            case "RecyclerView":
                b = new JSONRecyclerViewBuilder(mActivity, mContainer);

                break;

        }



        return b;
    }



}
