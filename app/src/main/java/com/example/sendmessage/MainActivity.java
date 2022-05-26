package com.example.sendmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Initialize variable
    EditText edtPhone,edtMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Assign variable
        edtPhone.findViewById(R.id.et_phone);
        edtMessage=findViewById(R.id.et_message);
        btnSend.findViewById(R.id.bt_send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){
                    sendMessage();
                }else{

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.SEND_SMS},100);
                }

            }
        });
    }

    private void sendMessage(){
        String sPhone=edtPhone.getText().toString().trim();
        String sMessage=edtMessage.getText().toString().trim();

        if(!sPhone.equals("")&&!sMessage.equals("")){
            // When both edit text value not equal to blank
            // Initialize sms manager
            SmsManager smsManager = SmsManager.getDefault();
            // Send text message
            smsManager.sendTextMessage(sPhone,null,sMessage
            ,null,null);
            // Display toast
            Toast.makeText(getApplicationContext()
                   , "SMS sent successfully!",Toast.LENGTH_LONG).show();
            // When edit text value is blank
            // Display toast

        }else{
            Toast.makeText(getApplicationContext()
                    ,"Enter value first.",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length > 0 && grantResults[0]
        == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else{
            Toast.makeText(getApplicationContext()
                    ,"Persmission Denied!", Toast.LENGTH_SHORT).show();
        }
    }
}