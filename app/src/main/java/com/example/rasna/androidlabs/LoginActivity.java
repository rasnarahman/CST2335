package com.example.rasna.androidlabs;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Editable;
import android.content.Intent;

public class LoginActivity extends Activity {
    String TAG = "activity_login.xml";
    Button msgButton ;
    EditText passwordMsg;
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordMsg = (EditText) findViewById(R.id.editText2);
        msgButton = (Button)findViewById(R.id.button2);

        setupMessageButton();


    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");

    }
    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");
    }

    private void setupMessageButton(){
        // msgButton = (Button)findViewById(R.id.button2);
        msgButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(TAG, "clicked the button");
                saveInfo(v);

            }
        });
    }

    public void saveInfo(View v){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mapTypeString = preferences.getString("DefaultEmail", "email@domain.com");



        Toast.makeText(this,"saved",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, StartActivity.class);
        startActivity(intent);


    }




}