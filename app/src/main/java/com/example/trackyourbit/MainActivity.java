package com.example.trackyourbit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    public String APPId = "MjMyMjk4NjVmZGVjNGUyMDljOTA3YzM5ZTJkYWU3OTQ";


    public String mSymbolSet = "global";
    public String mperiod = "hour";
    public String mCurrency;
    public int mLoc;
    TextView mRateTextView;
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRateTextView = findViewById(R.id.rateTextView);
        mSpinner = findViewById(R.id.spinner);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int pos, long id) {
                mLoc = pos;
                mCurrency = (String) parent.getItemAtPosition(mLoc);
                Log.d("Bitcoin", "Currency Selected is  " + mCurrency);

                someNetworking(mCurrency);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg1) {
                Log.d("Bitcoin", "Nothing S0");

            }


        });
    }

    public void someNetworking(String cur) {

        String mURL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTC"+cur;

        Log.d("Bitcoin",mURL);

        AsyncHttpClient mclient = new AsyncHttpClient();

        mclient.get(mURL, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("Bitcoin", "DATA COLLECTED" + response.toString());

                try {
                    String price = response.getString("last");
                    mRateTextView.setText(price);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.e("Bitcoin", "Fail  " + e.toString());
                Log.d("Bitcoin", "Fail..status code" + statusCode);

            }
        });
    }


}





