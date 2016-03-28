package com.example.tabeaux.mousecontroller;

import android.support.v4.view.VelocityTrackerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tabeaux.mousecontroller.app.AppController;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class testActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String DEBUG_TAG = "Velocity";
    private VelocityTracker mVelocityTracker = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button left_click=(Button)findViewById(R.id.left_click_button);
        Button right_click=(Button)findViewById(R.id.right_click_button);

        left_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("click","left");

                String url =  "http://192.168.43.149/mouse%20controller/click_update.php";
                Map<String, String> params = new HashMap<String, String>();
                params.put("click", Integer.toString(1));

                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Response: ", response.toString());
                    }
                });
                AppController.getInstance().addToRequestQueue(jsObjRequest);

            }
        });

        right_click.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("click","right");

                String url =  "http://192.168.43.149/mouse%20controller/click_update.php";
                Map<String, String> params = new HashMap<String, String>();
                params.put("click", Integer.toString(2));

                CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Response: ", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError response) {
                        Log.d("Response: ", response.toString());
                    }
                });
                AppController.getInstance().addToRequestQueue(jsObjRequest);
            }
        });

        View onTouchView = findViewById(R.id.track);
        onTouchView.setOnTouchListener(this);

    }

    public boolean onTouch(View view, MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_MOVE)
        {
            float x = event.getRawX();
            float y = event.getRawY();
            int x_c=(int)x;
            int y_c=(int)y;

            //Log.d("tag", Integer.toString(x_c));
            //Log.d("tag",Integer.toString(y_c));
            //  Code to display x and y go here


            String url =  "http://192.168.43.149/mouse%20controller/insert_database.php";
            Map<String, String> params = new HashMap<String, String>();
            params.put("x_coor", Integer.toString(x_c));
            params.put("y_coor",Integer.toString(y_c));


            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Response: ", response.toString());
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError response) {
                    Log.d("Response: ", response.toString());
                }
            });
            AppController.getInstance().addToRequestQueue(jsObjRequest);
        }
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
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
}
