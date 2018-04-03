package com.example.zyl.firtapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    String a;
    TextView textview;
    EditText editText;
    MyWarpView mwv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mwv= (MyWarpView) findViewById(R.id.mwv);
        mwv.setWarpView(R.drawable.jinta);

        textview = (TextView) findViewById(R.id.textview);
        textview.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.editText);
        editText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview:
                startActivityForResult(new Intent(this, TwoActivity.class), 101);
                break;
            case R.id.editText:
                startActivityForResult(new Intent(this, ThreeActivity.class), 102);
                break;
        }
    }
    public void toRecycler(View v){
        startActivity(new Intent(this, ScrollNestActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case 101:
                    String texts = data.getStringExtra("text");
                    textview.setText(texts);
                    break;
                case 102:
                    String three = data.getStringExtra("three");
                    editText.setText(three);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
