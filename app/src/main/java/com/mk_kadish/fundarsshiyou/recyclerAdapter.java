package com.mk_kadish.fundarsshiyou;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mk_kadish.fundarsshiyou.Game1.game1_HomeActivity;
import com.mk_kadish.fundarsshiyou.Game2.game2_HomeActivity;
import com.mk_kadish.fundarsshiyou.Game3.game3_HomeActivity;
import com.mk_kadish.fundarsshiyou.Game4.game4_HomeActivity;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ImageViewHolder> {
    private Context context;
    private int[] images;
    private int screenWidth;

    public recyclerAdapter(int[] images,Context context,int sw)
    {
        this.images=images;
        this.context=context;
        screenWidth=sw;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.album_layout,parent,false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = screenWidth / 2;
        view.setLayoutParams(layoutParams);


        ImageViewHolder imageViewHolder=new ImageViewHolder(view,context,images);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position)
    {
        int image_id=images[position];
        holder.album.setImageResource(image_id);
      //  holder.album_title.setText("Image:"+position);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView album;
       // TextView album_title;
        Context context;
        int[] images;

        public ImageViewHolder(@NonNull View itemView,Context context,int[] images ) {
            super(itemView);
            album=itemView.findViewById(R.id.album);
            itemView.setOnClickListener(this);
            this.context=context;
            this.images=images;
        }

        @Override
        public void onClick(View v) {
            int curPos = images[getAdapterPosition()];

            switch (curPos)
            {
                case R.drawable.l1p1g1numbers:
                    context.startActivity(new Intent(context, game3_HomeActivity.class));
                    break;
                case R.drawable.l1p1g2animals:
                    context.startActivity(new Intent(context, game2_HomeActivity.class));
                    break;
                case R.drawable.l1p1g2words:
                    context.startActivity(new Intent(context, game1_HomeActivity.class));
                    break;
                case R.drawable.l1p1g2arrangesentence:
                    context.startActivity(new Intent(context, game4_HomeActivity.class));
                    break;
            }
        }
    }
}
