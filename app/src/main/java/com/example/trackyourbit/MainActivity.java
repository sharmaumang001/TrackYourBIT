package com.example.trackyourbit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Currency;


public class MainActivity extends AppCompatActivity {

    public String BitURL = "g";
    public String APPId = "z";


    public String Currency;
    public int loc;
    TextView mRateTextView;
    Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRateTextView = findViewById(R.id.rateTextView);
        mSpinner= findViewById(R.id.spinner);


        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.currency_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);


        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view1, int pos, long id)
            {
                loc = pos;
                Currency = (String) parent.getItemAtPosition(loc);
                Log.d("Bitcoin","Currency Selected is  "+ Currency);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg1)
            {
                Log.d("Bitcoin","Nothing S0");

            }
        });
    }





}
