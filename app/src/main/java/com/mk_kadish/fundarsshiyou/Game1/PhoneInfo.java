package com.mk_kadish.fundarsshiyou.Game1;

import android.util.DisplayMetrics;
import android.view.WindowManager;

public class PhoneInfo {


    public void phoneInfo() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
    }
}

