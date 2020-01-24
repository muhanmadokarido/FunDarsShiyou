package com.mk_kadish.fundarsshiyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mk_kadish.fundarsshiyou.Admin.sharedPreferenceConfig;

public class althaniActivity extends AppCompatActivity {
    private sharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_althani);
        preferenceConfig=new sharedPreferenceConfig(getApplicationContext());
    }

    public void startLearn1(View view)
    {
        startActivity(new Intent(this, althalithActivity.class));
        finish();
    }

}
