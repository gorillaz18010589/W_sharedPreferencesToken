package com.example.okhttptoken;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String token;
    private EditText edit_input;
    private Button btn_save,btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        SharedPreferences sharedPreferences = getSharedPreferences("a", Context.MODE_PRIVATE);

        String getToken = sharedPreferences.getString(token, "");
        if (getToken == token) {
            Intent intent = new Intent(MainActivity.this,Page2Activity.class);
            startActivity(intent);
            Log.v("hank","true:" +  sharedPreferences.getString(token, ""));
        }else{
            Log.v("hank","false:" + "token不對");
        }

    }

    private void init() {
        edit_input = findViewById(R.id.edit_token);
        btn_save = findViewById(R.id.btn_saveToken);
        btn_read = findViewById(R.id.btn_readToken);

        btn_read.setOnClickListener(this);
        btn_save.setOnClickListener(this);

    }

    public void saveToken(String token) {

        SharedPreferences sharedPreferences = getSharedPreferences("a", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(token, token);
        editor.commit();

        Log.v("hank", "saveToken:" + sharedPreferences.getString(token, token));
    }


    public boolean isToken(String token) {
        boolean isToken = false;
        SharedPreferences sharedPreferences = getSharedPreferences("a", Context.MODE_PRIVATE);

        String getToken = sharedPreferences.getString(token, "");
        if (getToken == token) {
            isToken = true;
            Log.v("hank","true:" +  sharedPreferences.getString(token, ""));
        }else{
            isToken = false;
            Log.v("hank","false:" + isToken);
        }

        return isToken;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_saveToken:
                token = edit_input.getText().toString();
                Log.v("hank", "token:" + edit_input.getText().toString());
                saveToken(token);
                break;

            case R.id.btn_readToken:
//                isToken(token);
                Log.v("hank", "token:" + token);
                break;
        }
    }
}
