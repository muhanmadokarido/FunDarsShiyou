package com.mk_kadish.fundarsshiyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class althalithActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althalith);
    }

    public void startLevel1(View view) {
        startActivity(new Intent(this, indexActivity.class));
        finish();
    }
}
