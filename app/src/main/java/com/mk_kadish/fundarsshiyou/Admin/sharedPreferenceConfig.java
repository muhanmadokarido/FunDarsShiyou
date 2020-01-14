package com.mk_kadish.fundarsshiyou.Admin;

import android.content.Context;
import android.content.SharedPreferences;

import com.mk_kadish.fundarsshiyou.R;

public class sharedPreferenceConfig
{
    private SharedPreferences sharedPreferences;
    private Context context;

    public sharedPreferenceConfig(Context context)
    {
        this.context=context;
        this.sharedPreferences=context.getSharedPreferences(context.getResources().getString(R.string.Login_preference),Context.MODE_PRIVATE);
    }

    public void writeLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference),status);
        editor.commit();
    }

    public boolean readLoginStatus()
    {
        boolean status=false;
        status=sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference),false);
        return status;
    }
}