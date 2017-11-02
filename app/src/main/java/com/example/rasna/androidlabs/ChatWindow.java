package com.example.rasna.androidlabs;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class ChatWindow extends Activity {

    protected static final String ACTIVITY_NAME = "ChatWindow";
    String TAG = "activity_chat_window.xml";
    Button sendBtn;
    EditText editTxt;
    ArrayList <String> storeChat = new ArrayList <String>();
    ChatAdapter messageAdapter;
    TextView message;
    ChatDatabaseHelper chatHelper;
    ContentValues newValues = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        Resources resources = getResources();
        Context context = getApplicationContext();
        final ListView list = (ListView)findViewById(R.id.listViewChat);
        messageAdapter = new ChatAdapter( this );
        list.setAdapter (messageAdapter);
        editTxt = (EditText)findViewById(R.id.editTextChat);
        sendBtn = (Button)findViewById(R.id.buttonSend);

        chatHelper = new ChatDatabaseHelper(context);
        //SQLiteDatabase chatDB = chatHelper.getMdb();
        newValues = new ContentValues();



        chatHelper.open();

        Cursor cursor = chatHelper.getChatMssages();
        if(cursor.moveToFirst()){
            do{
                String msg = chatHelper.getMeessageFromCursor(cursor);
                Log.i(ACTIVITY_NAME, "SQL Message: " +  msg);
                Log.i(ACTIVITY_NAME, "Cursor's column count=" + cursor.getColumnCount());
                storeChat.add(msg);
                cursor.moveToNext();
            }while(!cursor.isAfterLast());
            messageAdapter.notifyDataSetChanged();

        }

        for (int i=0; i<cursor.getColumnCount(); i++) {
            Log.i(ACTIVITY_NAME, cursor.getColumnName(i));
        }

        sendBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.v("EditText", editTxt.getText().toString());
                String chatString = editTxt.getText().toString();
                storeChat.add(chatString);
                messageAdapter.notifyDataSetChanged();
                editTxt.setText("");
                newValues.put("message" , chatString);
                chatHelper.insert(newValues);
            }
        });
    }

    protected void onDestroy(){
        super.onDestroy();
        if(chatHelper != null ){
            chatHelper.close();
        }
    }



    private class ChatAdapter extends ArrayAdapter<String>
    {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount(){
            return storeChat.size();
        }

        public String getItem(int position){
            return storeChat.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
            message = (TextView)result.findViewById(R.id.messageText);
            message.setText(   getItem(position)  ); // get the string at position
            return result;
        }
    }

}