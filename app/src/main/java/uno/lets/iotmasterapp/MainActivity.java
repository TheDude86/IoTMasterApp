package uno.lets.iotmasterapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import JSONParsingFactory.JSONBuilder;
import JSONParsingFactory.JSONBuilderFactory;
import JSONParsingFactory.JSONButtonBuilder;
import JSONParsingFactory.JSONCardViewBuilder;
import JSONParsingFactory.JSONImageViewBuilder;
import JSONParsingFactory.JSONRecyclerViewBuilder;
import JSONParsingFactory.JSONTextViewBuilder;
import util.JSONAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public JSONObject mData = new JSONObject();
    public HashMap<Integer, View> mViews = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RelativeLayout main = (RelativeLayout) findViewById(R.id.content_main);

        try {

            mData.put("Light0", false);
            mData.put("Light0 Color", 0);
            mData.put("Light1", false);
            mData.put("Light1 Color", 0);
            mData.put("Light2", false);
            mData.put("Light2 Color", 0);

//            JSONObject json = new JSONObject();
//            json.put("Type", "RecyclerView");
//            json.put("Width", JSONBuilder.MATCH_PARENT);
//            json.put("Height", JSONBuilder.MATCH_PARENT);
//            json.put("Margin Left", 0);
//            json.put("Margin Right", 0);
//            json.put("Margin Top", 0);
//            json.put("Align Parent Top", true);
//            json.put("Align Parent Left", true);
//            json.put("Alpha", 1);
//            json.put("Count", 3);
//            json.put("ID", 0);
//
//            JSONObject adapter = new JSONObject();
//            adapter.put("Type", "CardView");
//            adapter.put("Width", JSONBuilder.MATCH_PARENT);
//            adapter.put("Height", JSONBuilder.WRAP_CONTENT);
//            adapter.put("Margin Left", 30);
//            adapter.put("Margin Right", 30);
//            adapter.put("Margin Bottom", 40);
//            adapter.put("Align Parent Top", true);
//            adapter.put("Align Parent Left", true);
//            adapter.put("Alpha", 1);
//            adapter.put("Count", 3);
//            adapter.put("ID", 1);
//
//            JSONArray child = new JSONArray();
//
//            JSONObject container = new JSONObject();
//            container.put("Type", "RelativeLayout");
//            container.put("Width", JSONBuilder.MATCH_PARENT);
//            container.put("Height", JSONBuilder.WRAP_CONTENT);
//            container.put("Margin Left", 0);
//            container.put("Margin Right", 0);
//            container.put("Margin Top", 0);
//            container.put("Align Parent Top", true);
//            container.put("Align Parent Left", true);
//            container.put("Alpha", 1);
//            container.put("Count", 3);
//            container.put("ID", 2);
//
//            JSONArray children = new JSONArray();
//
//            JSONObject button = new JSONObject();
//            button.put("Type", "Button");
//            button.put("Name", "Button");
//            button.put("Width", JSONBuilder.WRAP_CONTENT);
//            button.put("Height", JSONBuilder.WRAP_CONTENT);
//            button.put("Margin Left", 50);
//            button.put("Margin Right", 0);
//            button.put("Margin Top", 50);
//            button.put("Margin Bottom", 50);
//            button.put("Align Parent Top", true);
//            button.put("Align Parent Left", true);
//            button.put("Alpha", 1);
//            button.put("ID", 3);
//
//            JSONObject text = new JSONObject();
//            text.put("Type", "TextView");
//            text.put("Name", "Text");
//            text.put("Width", JSONBuilder.WRAP_CONTENT);
//            text.put("Height", JSONBuilder.WRAP_CONTENT);
//            text.put("Margin Left", 0);
//            text.put("Margin Right", 50);
//            text.put("Margin Top", 0);
//            text.put("Center Vertical", true);
//            text.put("Align Parent Right", true);
//            text.put("Alpha", 1);
//            text.put("ID", 4);
//
//            children.put(button);
//            children.put(text);
//
//            container.put("Children", children);
//
//
//            child.put(container);
//
//
//            adapter.put("Children", child);
//            json.put("Adapter", adapter);



            JSONArray children = new JSONArray();
            JSONObject container = new JSONObject();
            container.put("Type", "RelativeLayout");
            container.put("Width", JSONBuilder.MATCH_PARENT);
            container.put("Height", JSONBuilder.WRAP_CONTENT);
            container.put("Margin Left", 0);
            container.put("Margin Right", 0);
            container.put("Margin Top", 0);
            container.put("Align Parent Top", true);
            container.put("Align Parent Left", true);
            container.put("Alpha", 1);
            container.put("ID", 1);


            JSONObject json = new JSONObject();
            json.put("Type", "Switch");
            json.put("Name", "Button");
            json.put("Width", JSONBuilder.WRAP_CONTENT);
            json.put("Height", JSONBuilder.WRAP_CONTENT);
            json.put("Margin Left", 50);
            json.put("Margin Right", 0);
            json.put("Margin Top", 50);
            json.put("Margin Bottom", 50);
            json.put("Align Parent Top", true);
            json.put("Align Parent Left", true);
            json.put("Alpha", 1);
            json.put("ID", 5);

            JSONArray actions = new JSONArray();
            JSONObject action1 = new JSONObject();
            action1.put("Type", "Edit");
            action1.put("Key", "Light%s");
            action1.put("Order", 0);

            JSONObject action2 = new JSONObject();
            action2.put("Type", "Update");
            action2.put("Row", "%s");
            action2.put("Row ID", 6);
            action2.put("ID", 2);
            action2.put("Text", "New Value %s");
            action2.put("Order", 1);

            actions.put(action1);
            actions.put(action2);
            json.put("Actions", actions);



            JSONObject text = new JSONObject();
            text.put("Type", "TextView");
            text.put("Name", "Text");
            text.put("Width", JSONBuilder.WRAP_CONTENT);
            text.put("Height", JSONBuilder.WRAP_CONTENT);
            text.put("Margin Left", 50);
            text.put("Margin Right", 100);
            text.put("Margin Top", 100);
            text.put("Margin Bottom", 50);
            text.put("Align Parent Top", true);
            text.put("Align Parent Right", true);
            text.put("Alpha", 1);
            text.put("ID", 6);

            JSONObject text2 = new JSONObject();
            text2.put("Type", "TextView");
            text2.put("Name", "Text");
            text2.put("Width", JSONBuilder.WRAP_CONTENT);
            text2.put("Height", JSONBuilder.WRAP_CONTENT);
            text2.put("Margin Left", 50);
            text2.put("Margin Right", 100);
            text2.put("Margin Top", 300);
            text2.put("Margin Bottom", 50);
            text2.put("Align Parent Top", true);
            text2.put("Align Parent Right", true);
            text2.put("Alpha", 1);
            text2.put("ID", 7);

            JSONObject button = new JSONObject();
            button.put("Type", "Button");
            button.put("Name", "  Change Color  ");
            button.put("Width", JSONBuilder.WRAP_CONTENT);
            button.put("Height", JSONBuilder.WRAP_CONTENT);
            button.put("Margin Left", 50);
            button.put("Margin Right", 0);
            button.put("Margin Top", 200);
            button.put("Margin Bottom", 50);
            button.put("Align Parent Top", true);
            button.put("Align Parent Left", true);
            button.put("Alpha", 1);
            button.put("ID", 3);

            JSONObject action3 = new JSONObject();
            action3.put("Type", "Layout");
            action3.put("Layout", "Dialog Color");
            action3.put("Order", 0);

            JSONObject action4 = new JSONObject();
            action4.put("Type", "Update");
            action4.put("Row", "%s");
            action4.put("Row ID", 3);
            action4.put("ID", 2);
            action4.put("Background", "%s");
            action4.put("Order", 1);

            JSONObject action5 = new JSONObject();
            action5.put("Type", "Edit");
            action5.put("Key", "Light%s Color");
            action5.put("Order", 2);

            JSONArray buttonActions = new JSONArray();
            buttonActions.put(action3);
            buttonActions.put(action4);
            buttonActions.put(action5);


            button.put("Actions", buttonActions);




            children.put(json);
            children.put(text);
            children.put(text2);
            children.put(button);

            container.put("Children", children);




            JSONObject card = new JSONObject();
            card.put("Type", "CardView");
            card.put("Width", JSONBuilder.MATCH_PARENT);
            card.put("Height", JSONBuilder.WRAP_CONTENT);
            card.put("Margin Left", 30);
            card.put("Margin Right", 30);
            card.put("Margin Bottom", 40);
            card.put("Align Parent Top", true);
            card.put("Align Parent Left", true);
            card.put("Alpha", 1);
            card.put("Count", 3);
            card.put("ID", 0);

            JSONArray kids = new JSONArray();
            kids.put(container);

            card.put("Children", kids);

            JSONObject recycler = new JSONObject();
            recycler.put("Type", "RecyclerView");
            recycler.put("Width", JSONBuilder.MATCH_PARENT);
            recycler.put("Height", JSONBuilder.MATCH_PARENT);
            recycler.put("Margin Left", 0);
            recycler.put("Margin Right", 0);
            recycler.put("Margin Top", 0);
            recycler.put("Align Parent Top", true);
            recycler.put("Align Parent Left", true);
            recycler.put("Alpha", 1);
            recycler.put("Count", 3);
            recycler.put("ID", 2);

            recycler.put("Adapter", card);

            JSONBuilder builder = new JSONBuilderFactory(this, main).getBuilder(recycler);
            builder.Build(recycler);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
