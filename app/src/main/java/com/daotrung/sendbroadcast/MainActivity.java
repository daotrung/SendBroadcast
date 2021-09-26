package com.daotrung.sendbroadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar ;

    private static final String MY_ACTION = "com.trungdao.ACTION";
    private static final String MY_NAME = "com.trungdao.TEXT1";
    private static final String MY_ADDRESS = "com.trungdao.TEXT2";
    private static final String MY_PHONE = "com.trungdao.TEXT3";
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(MY_ACTION.equals(intent.getAction())){
                String text1 = intent.getStringExtra(MY_NAME);
                txt1.setText(text1);
                String text2 = intent.getStringExtra(MY_ADDRESS);
                txt2.setText(text2);
                String text3 = intent.getStringExtra(MY_PHONE);
                txt3.setText(text3);

            }
        }
    };
    Button btnSend ;
    EditText edtName , edtPhone ,edtAddress ;
    TextView txt1 , txt2 ,txt3 ,txtToolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = findViewById(R.id.btn_send);
        edtName = findViewById(R.id.send_name);
        edtAddress = findViewById(R.id.send_address);
        edtPhone = findViewById(R.id.send_phone);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txtToolbar = findViewById(R.id.txtToolbar);
        toolbar = findViewById(R.id.toolbar_main);

        // toolbar
        toolbar.setTitle(txtToolbar.getText());
        setSupportActionBar(toolbar);
     getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSendBroadCast();
            }
        });

    }
    private void clickSendBroadCast() {
        Intent intent = new Intent(MY_ACTION);
        String check1 = edtName.getText().toString() ;
        String check2 = edtAddress.getText().toString() ;
        String check3 = edtPhone.getText().toString() ;
        if(check1.isEmpty() || check2.length() == 0 || check3.equals("") ) {
            Toast.makeText(MainActivity.this, "Chua Nhap du lieu , xin hay nhap lại ", Toast.LENGTH_SHORT).show();
        }else{
            intent.putExtra(MY_NAME, edtName.getText().toString());
            intent.putExtra(MY_PHONE,edtPhone.getText().toString());
            intent.putExtra(MY_ADDRESS,edtAddress.getText().toString());
        }
        sendBroadcast(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(MY_ACTION);
        registerReceiver(mBroadcastReceiver,intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(mBroadcastReceiver);
    }
}