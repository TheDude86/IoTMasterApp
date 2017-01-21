package JSONParsingFactory;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import org.json.JSONException;
import org.json.JSONObject;

import util.JSONAdapter;

/**
 * Created by novosejr on 1/21/2017.
 */

public class JSONRecyclerViewBuilder extends JSONViewBuilder {


    public JSONRecyclerViewBuilder(AppCompatActivity a, ViewGroup c) {
        super(a, c);
    }

    @Override
    public void Build(JSONObject json) throws JSONException {

        RecyclerView r = new RecyclerView(mActivity);
        r.setLayoutManager(new GridLayoutManager(mActivity, 1));


        r.setAdapter(new JSONAdapter(mActivity, json));


        super.Build(r, json);
    }
}
