package JSONParsingFactory;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import uno.lets.iotmasterapp.MainActivity;
import util.JSONAdapter;

/**
 * Created by novosejr on 1/20/2017.
 */

public abstract class JSONBuilder {
    public static final int MATCH_PARENT = -1;
    public static final int WRAP_CONTENT = -2;
    public static final int NONE = 0;

    public HashMap<Integer, View> mViews = new HashMap<>();
    public AppCompatActivity mActivity;
    public ViewGroup mContainer;
    public JSONObject mJSON;
    public int mRow = 0;

    public JSONBuilder(AppCompatActivity a, ViewGroup c) {
        mActivity = a;
        mContainer = c;

    }

    public boolean has(String s) {

        return mJSON.has(s);
    }

    public void editValue(Object value, String key) {
        try {



            Object o = ((MainActivity) mActivity).mData.get(key);
            Log.println(Log.ASSERT, "TEST", value.getClass().toString() + " " + o.getClass().toString());

            if (value.getClass().equals(o.getClass())) {
                ((MainActivity) mActivity).mData.put(key, value);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateValue(Object value, JSONObject j) throws JSONException {

        View v = ((MainActivity) mActivity).mViews.get(j.getInt("ID"));

        if (j.has("Text")) {
            if (v instanceof Button)
                ((Button) v).setText(String.format(j.getString("Text"), value.toString()));
            else if (v instanceof TextView)
                ((TextView) v).setText(String.format(j.getString("Text"), value.toString()));

        }

        if (j.has("Background")) {
            v.setBackgroundColor((int) value);

        }



    }


    public void updateValue(Object value, JSONObject json, String row) throws JSONException {

        View v = ((MainActivity) mActivity).mViews.get(json.getInt("ID"));

        if (v instanceof RecyclerView) {
            JSONAdapter adapter = (JSONAdapter) ((RecyclerView) v).getAdapter();
            adapter.updateValue(value, json, row);

        }


    }

    public void editLayout(String type, final ArrayList<JSONObject> actions) {

        if (type.equals("Dialog Color")) {
            ColorPickerDialogBuilder
                    .with(mActivity)
                    .setTitle("Choose color")
                    .initialColor(Color.WHITE)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {


                        }
                    })
                    .setPositiveButton("Okay", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {

                            for (JSONObject j: actions) {

                                try {
                                    switch (j.getString("Type")) {
                                        case "Edit":

                                            editValue(selectedColor, String.format(j.getString("Key"), Integer.toString(mRow)));

                                            break;
                                        case "Update":

                                            if (j.has("Row"))
                                                updateValue(selectedColor, j, String.format(j.getString("Row"), Integer.toString(mRow)));
                                            else
                                                updateValue(selectedColor, j);

                                            break;
                                        case "Layout":
                                            ArrayList<JSONObject> remaining = (ArrayList<JSONObject>) actions.clone();


                                            remaining.remove(j);
                                            editLayout(j.getString("Layout"), remaining);

                                            break;

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            Log.println(Log.ASSERT, "TAG", ((MainActivity) mActivity).mData.toString());

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .build()
                    .show();

        }

    }

    public ArrayList<JSONObject> sortActions(JSONArray actions) throws JSONException {
        ArrayList<JSONObject> sortedActions = new ArrayList<>();

        for (int i = 0; i < actions.length(); i++) {
            sortedActions.add(actions.getJSONObject(i));
        }

        Collections.sort(sortedActions, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject j1, JSONObject j2) {
                try {
                    int i = j1.getInt("Order");
                    int j = j2.getInt("Order");

                    if (i > j)
                        return 1;

                    if (j < i)
                        return -1;

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });


        return sortedActions;
    }

    abstract public void Build(JSONObject json) throws JSONException;

    abstract public void Build(View v, JSONObject json) throws JSONException;

}
