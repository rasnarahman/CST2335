package com.example.rasna.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CheckBox;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

public class ListItemsActivity extends Activity {
    private static final int REQUEST_IMAGE_CAPTURE = 10;
    private static final int TAKE_PHOTO_REQUEST = 10;
    ImageButton imgBtn;
    String TAG = "activity_list_items.xml";
    // initiate a Switch
    Switch simpleSwitch;
    CheckBox simpleCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        imgBtn  = (ImageButton)findViewById(R.id.image1);
        simpleSwitch = (Switch) findViewById(R.id.switch1);
        simpleCheckBox = (CheckBox) findViewById(R.id.checkBox1);
        onClick();
        setOnCheckedChanged();
        OnCheckChanged();

    }
    public void onClick(){

        imgBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(TAG, "clicked the button");
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhotoIntent, TAKE_PHOTO_REQUEST);
                onActivityResult(10,10, takePhotoIntent);
            }

            private void getOutputMediaFileUri(int mediaTypeImage) {
            }
        });

    }

    public void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 10){
            Log.i(TAG, "Returned to StartActivity.onActivityResult");
            if (requestCode == REQUEST_IMAGE_CAPTURE && responseCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imgBtn.setImageBitmap(imageBitmap);
            }
        }
    }

    public void setOnCheckedChanged(){
        // check current state of a Switch (true or false).
        String statusSwitch1;
        Boolean switchState = simpleSwitch.isChecked();
        if(switchState){
            Toast.makeText(this,"Switch is on",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Switch is off",Toast.LENGTH_LONG).show();
        }

    }


    public void  OnCheckChanged(){
        simpleCheckBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(TAG, "on check !");

                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml
                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Response", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();
            }

        });

    }
}
