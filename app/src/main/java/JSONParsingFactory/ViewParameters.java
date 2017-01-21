package JSONParsingFactory;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by novosejr on 1/20/2017.
 */

@SuppressWarnings("ResourceType")
public class ViewParameters {
    static JSONObject mJSON;

    /**
     * RL stands for RelativeLayout
     *
     * @param v
     * @param json
     * @return
     * @throws JSONException
     */
    public static ViewGroup.LayoutParams AddRLParameters(View v, JSONObject json) throws JSONException {
        mJSON = json;

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(json.getInt("Width"), json.getInt("Height"));



        if (has("Align Parent Left") && json.getBoolean("Align Parent Left"))
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        if (has("Align Parent Right") && json.getBoolean("Align Parent Right"))
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        if (has("Align Parent TOP") && json.getBoolean("Align Parent TOP"))
            params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        if (has("Align Parent Bottom") && json.getBoolean("Align Parent Bottom"))
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        if (has("Center Horizontal") && json.getBoolean("Center Horizontal"))
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);

        if (has("Center Vertical") && json.getBoolean("Center Vertical"))
            params.addRule(RelativeLayout.CENTER_VERTICAL);


        int left = has("Margin Left") ? json.getInt("Margin Left") : 0;
        int right = has("Margin Right") ? json.getInt("Margin Right") : 0;
        int top = has("Margin Top") ? json.getInt("Margin Top") : 0;
        int bottom = has("Margin Bottom") ? json.getInt("Margin Bottom") : 0;

        params.setMargins(left, top, right, bottom);




        left = has("Padding Left") ? json.getInt("Padding Left") : 0;
        right = has("Padding Right") ? json.getInt("Padding Right") : 0;
        top = has("Padding Top") ? json.getInt("Padding Top") : 0;
        bottom = has("Padding Bottom") ? json.getInt("Padding Bottom") : 0;

        v.setPadding(left, top, right, bottom);



        return params;
    }

    public static boolean has(String s) {

        return mJSON.has(s);
    }

}
