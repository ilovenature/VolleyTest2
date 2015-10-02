package com.xlab.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocketFactory;

import static com.android.volley.Response.*;

/**
 * 此專案使用了singleton pattern
 * volley library  supports HTTPS
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView response = (TextView) findViewById(R.id.response_text);

        String url = "http://www.google.com";
        String urls = "https://ipost.post.gov.tw/web/CSController";
        String url2 = "https://portal.ncu.edu.tw/login;jsessionid=31D666037A81807F802E41EEA9706145";


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        // Display the first 500 characters of the response string.
                        response.setText("Response is: "+ s);
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                response.setText("That didn't work!");
            }
                });
        stringRequest.setTag("tag");

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        /*
        以下範例程式碼為利用ImageLoader下載圖片並設到ImageView
        ImageLoader imageLoader = MySingleton.getInstance(this).getImageLoader();
        imageLoader.get(requestUrl, ImageLoader.getImageListener(imageview, R.drawable.def_image, R.drawable.err_image));
        */


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
