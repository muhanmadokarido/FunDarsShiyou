package com.mk_kadish.fundarsshiyou.Game1;


import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mk_kadish.fundarsshiyou.R;


public class ImageAdapter extends BaseAdapter {
    private final Context context;
    private int screenLength;
    private  int screenWidth;
    public int elementCount;

    public ImageAdapter(Context context,int screenLength, int screenWidth,int arraySize) {
        this.context = context;
        this.screenLength=screenLength;
        this.screenWidth=screenWidth;
        this.elementCount=arraySize;
    }

    @Override
    public int getCount() {
        return elementCount;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(250, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else imageView = (ImageView)convertView;
        imageView.setImageResource(R.drawable.questionbg);
        return imageView;
    }
}