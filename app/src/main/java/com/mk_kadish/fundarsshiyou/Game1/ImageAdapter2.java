package com.mk_kadish.fundarsshiyou.Game1;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.mk_kadish.fundarsshiyou.R;

import java.util.ArrayList;


public class ImageAdapter2 extends BaseAdapter {
    private final Context context;
    private int screenLength;
    private  int screenWidth;
    ArrayList<Integer> drawable;
    private final ArrayList<Integer> pos;

    public ImageAdapter2(Context context, int screenLength, int screenWidth,  ArrayList<Integer> drawable,  ArrayList<Integer> pos) {
        this.context = context;
        this.screenLength=screenLength;
        this.screenWidth=screenWidth;
        this.drawable=drawable;
        this.pos=pos;
    }

    @Override
    public int getCount() {
        return drawable.size();
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
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else imageView = (ImageView)convertView;
        imageView.setImageResource(R.drawable.hidden);
        return imageView;
    }

    public void removeItem(int position){
        drawable.remove(position);
        pos.remove(position);
        notifyDataSetChanged();
    }
}