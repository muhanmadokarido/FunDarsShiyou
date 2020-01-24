package com.mk_kadish.fundarsshiyou;

import android.content.Context;
import android.content.SharedPreferences;;

public class AllSharedPrefernces
{
    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences2;
    private Context context;

    public AllSharedPrefernces(Context context)
    {
        this.context=context;
        this.sharedPreferences=context.getSharedPreferences("Pref2",Context.MODE_PRIVATE);
        this.sharedPreferences2=context.getSharedPreferences("mkPoints",Context.MODE_PRIVATE);
    }

    public void writeTimer1(long timerValue)
    {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putLong(context.getResources().getString(R.string.Game1Timer),timerValue);
        editor.commit();
    }

    public Long readTimer1()
    {
        Long s1 = sharedPreferences.getLong(context.getResources().getString(R.string.Game1Timer), 0);
        return s1;
    }

    public Long increasePointsCounter() {

        Long s1 = sharedPreferences2.getLong(context.getResources().getString(R.string.PointCounter), 0);
        SharedPreferences.Editor editor2=sharedPreferences2.edit();
        editor2.putLong(context.getResources().getString(R.string.PointCounter),s1+1);
        editor2.commit();
        return s1+1;
    }

    public void initPointCounter(Long l1)
    {
        Long initialval=l1+2;
        SharedPreferences.Editor editor2=sharedPreferences2.edit();
        editor2.putLong(context.getResources().getString(R.string.PointCounter),initialval);
        editor2.commit();
        Long s1 = sharedPreferences2.getLong(context.getResources().getString(R.string.PointCounter), 0);
        int t=0;
    }

    public Long readPoints()
    {
        Long s1 = sharedPreferences2.getLong(context.getResources().getString(R.string.PointCounter), 0);
        return s1;
    }
}