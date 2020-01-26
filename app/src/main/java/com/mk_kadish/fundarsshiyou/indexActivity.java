package com.mk_kadish.fundarsshiyou;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mk_kadish.fundarsshiyou.Admin.sharedPreferenceConfig;

public class indexActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private sharedPreferenceConfig preferenceConfig;
    private int[] images=
            {
                    R.drawable.l1p1g1numbers,
                    R.drawable.l1p1g2arrangesentence,
                    R.drawable.l1p1g2animals,
                    R.drawable.l1p1g2words,

            };
    private recyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);


       // Toolbar toolbar = findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(this,4);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        adapter=new recyclerAdapter(images,this,displayMetrics.widthPixels);
        recyclerView.setAdapter(adapter);
        preferenceConfig=new sharedPreferenceConfig(getApplicationContext());
    }

    public void logOut(View view)
    {
        preferenceConfig.writeStudentLoginStatus(false);
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}