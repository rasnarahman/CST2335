package com.example.rasna.androidlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;

public class StartActivity extends Activity {
    String TAG = "activity_start.xml";
    Button startButton;
    Button startChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        startButton = (Button)findViewById(R.id.buttonStart);

        buttonClickAction();
        onClickStartChat();
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
        Log.i(TAG, "onDestroy");
    }

    private void buttonClickAction(){
        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(TAG, "clicked the button");
                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);
                onActivityResult(10,10, intent);
            }
        });
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 10 ){
            if(responseCode == Activity.RESULT_OK){
                Log.i(TAG, "Returned to StartActivity.onActivityResult");
                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(this,"my information to share",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onClickStartChat(){
        startChat = (Button)findViewById(R.id.buttonChat);
        startChat.setPadding(0,100,0,0);

        startChat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(TAG, "User clicked Start Chat");
                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
             
                startActivityForResult(intent, 10);
                onActivityResult(10,10, intent);
            }
        });
    }


}