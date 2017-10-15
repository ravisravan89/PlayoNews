package com.ravisravan.playonews;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ravisravan.playonews.datamodels.Hit;
import com.ravisravan.playonews.datamodels.NewsModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView news_recycler_view;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Hit> hitsList;
    private EditText editText;
    private final int MY_PERMISSIONS_REQUEST = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        hitsList = new ArrayList<>();
        editText = (EditText) findViewById(R.id.et_category);
        //TODO: check Runtime Permissions for android M and above once things are in place.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            checkPermissions();
        } else {
            if (isNetworkAvailable()) {
                requestNews(editText.getText().toString().trim());
            } else {
                showSnackBar();
            }
        }


        editText.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    requestNews(editText.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });

        news_recycler_view = (RecyclerView) findViewById(R.id.news_recycler_view);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        news_recycler_view.setLayoutManager(mLayoutManager);

        news_recycler_view.setAdapter(new NewsRecyclerViewAdapter(hitsList));
    }

    private void showSnackBar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.content), "Please check network connection", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_NETWORK_STATE)) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.content), "Please grant permissions to proceed further", Snackbar.LENGTH_LONG);
                snackbar.show();
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE},
                        MY_PERMISSIONS_REQUEST);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            if (isNetworkAvailable()) {
                requestNews(editText.getText().toString().trim());
            } else {
                showSnackBar();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (isNetworkAvailable()) {
                        requestNews(editText.getText().toString().trim());
                    } else {
                        showSnackBar();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public void searchCategory(View view) {
        requestNews(editText.getText().toString().trim());
    }

    private void requestNews(String category) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://hn.algolia.com/api/v1/search?query=" + category;

        // Request a string response from the provided URL.
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // mTxtDisplay.setText("Response: " + response.toString());

                        NewsModel newsResponse = new Gson().fromJson(response.toString(), NewsModel.class);
                        hitsList.clear();
                        hitsList.addAll(newsResponse.getHits());
                        news_recycler_view.getAdapter().notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.e(TAG, error.toString());
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }
}
