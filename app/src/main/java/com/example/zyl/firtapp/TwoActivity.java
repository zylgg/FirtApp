package com.example.zyl.firtapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class TwoActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        RadioGroup rg_select = (RadioGroup) findViewById(R.id.rg_select);
        rg_select.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        String text = radioButton.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("text",text);
        setResult(RESULT_OK,intent);
        finish();
    }
}
