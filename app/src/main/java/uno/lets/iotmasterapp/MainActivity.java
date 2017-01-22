package uno.lets.iotmasterapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
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
import com.loopj.android.http.JsonHttpResponseHandler;
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
import cz.msebera.android.httpclient.Header;
import util.JSONAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public JSONObject mData = new JSONObject();
    public HashMap<Integer, View> mViews = new HashMap<>();
    RelativeLayout main;
    public boolean update = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        main = (RelativeLayout) findViewById(R.id.content_main);

        Calls.getInfo(new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    mData = response.getJSONObject("Data");

                    JSONObject face = response.getJSONObject("Interface");

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
                    container.put("Count", 3);
                    container.put("ID", 2);


                    JSONBuilder builder = new JSONBuilderFactory(MainActivity.this, main).getBuilder(face);
                    builder.Build(face);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });



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
            update = false;

            main.removeAllViews();

            Calls.getInfo(new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                    try {
                        mData = response.getJSONObject("Data");

                        JSONObject face = response.getJSONObject("Interface");

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
                        container.put("Count", 3);
                        container.put("ID", 2);


                        JSONBuilder builder = new JSONBuilderFactory(MainActivity.this, main).getBuilder(face);
                        builder.Build(face);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        } else if (id == R.id.nav_gallery) {
            update = true;

            main.removeAllViews();
            new LongOperation().execute("");

            try {
                JSONObject json = new JSONObject();
                json.put("Type", "RelativeLayout");
                json.put("Name", "Test");
                json.put("Width", JSONBuilder.MATCH_PARENT);
                json.put("Height", JSONBuilder.MATCH_PARENT);
                json.put("Center Horizontal", true);
                json.put("Center Vertical", true);
                json.put("Alpha", 1);
                json.put("ID", 0);



                JSONObject timer = new JSONObject();
                timer.put("Type", "TextView");
                timer.put("Name", "Time Left: X");
                timer.put("Size", 40);
                timer.put("Width", JSONBuilder.WRAP_CONTENT);
                timer.put("Height", JSONBuilder.WRAP_CONTENT);
                timer.put("Margin Left", 20);
                timer.put("Margin Right", 20);
                timer.put("Margin Top", 20);
                timer.put("Margin Bottom", 20);
                timer.put("Center Horizontal", true);
                timer.put("Center Vertical", true);
                timer.put("Alpha", 1);
                timer.put("ID", 0);

                JSONObject button = new JSONObject();
                button.put("Type", "Button");
                button.put("Name", "  +30s  ");
                button.put("Width", JSONBuilder.WRAP_CONTENT);
                button.put("Height", JSONBuilder.WRAP_CONTENT);
                button.put("Margin Left", 20);
                button.put("Margin Right", 20);
                button.put("Margin Top", 20);
                button.put("Margin Bottom", 20);
                button.put("Center Horizontal", true);
                json.put("Align Parent Top", true);
                button.put("Alpha", 1);
                button.put("ID", 0);


                JSONArray children = new JSONArray();
                children.put(timer);
                children.put(button);


                json.put("Children", children);


                JSONBuilder builder = new JSONBuilderFactory(MainActivity.this, main).getBuilder(json);
                builder.Build(json);



            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.interrupted();
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_LONG).show();

//            if (update)
//                new LongOperation().execute("");


        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
