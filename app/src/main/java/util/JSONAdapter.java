package util;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import JSONParsingFactory.JSONBuilder;
import JSONParsingFactory.JSONBuilderFactory;
import uno.lets.iotmasterapp.R;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONAdapter extends RecyclerView.Adapter<JSONAdapter.Holder> {
    AppCompatActivity mActivity;
    JSONObject mJSON;

    ArrayList<HashMap <Integer, View>> mElements = new ArrayList<>();


    public JSONAdapter(AppCompatActivity a, JSONObject j) {
        mActivity = a;
        mJSON = j;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_adapter, parent, false);

        return new Holder(mView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        try {
            JSONBuilderFactory f = new JSONBuilderFactory(mActivity, holder.mLayout);
            JSONObject j = mJSON.getJSONObject("Adapter");

            JSONBuilder b = f.getBuilder(j);
            b.mRow = holder.getAdapterPosition();

            b.Build(j);

            mElements.add(position, b.mViews);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        int i = 1;

        try {
            i = mJSON.getInt("Count");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return i;
    }

    public void updateValue(Object value, JSONObject j, String row) throws JSONException {
        HashMap<Integer, View> element = mElements.get(Integer.parseInt(row));

        View v = element.get(j.getInt("Row ID"));

        if (v instanceof TextView && j.has("Text"))
            if (value instanceof Boolean) {
                ((TextView) v).setText(String
                        .format(String.format( ((Boolean) value) ?
                                        j.getString("True Text") : j.getString("False Text"),
                                value.toString())));

            } else {
                ((TextView) v).setText(String.format(j.getString("Text"), value.toString()));

            }


        if (j.has("Background"))
            v.setBackgroundColor((int) value);


    }

    protected class Holder extends RecyclerView.ViewHolder {
        RelativeLayout mLayout;

        public Holder(View itemView) {
            super(itemView);

            mLayout = (RelativeLayout) itemView.findViewById(R.id.main);


        }
    }

}
