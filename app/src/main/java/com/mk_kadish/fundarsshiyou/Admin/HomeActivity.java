package com.mk_kadish.fundarsshiyou.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mk_kadish.fundarsshiyou.Admin.sharedPreferenceConfig;
import com.mk_kadish.fundarsshiyou.Admin.userManagementActivity;
import com.mk_kadish.fundarsshiyou.MainActivity;
import com.mk_kadish.fundarsshiyou.R;

public class HomeActivity extends AppCompatActivity {

    private sharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        preferenceConfig=new sharedPreferenceConfig(getApplicationContext());
    }

    public void logOut(View view)
    {
        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void toUserManagement(View view)
    {
        startActivity(new Intent(this, userManagementActivity.class));
        finish();
    }
}
