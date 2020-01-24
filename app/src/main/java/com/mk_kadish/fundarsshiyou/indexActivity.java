package com.mk_kadish.fundarsshiyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mk_kadish.fundarsshiyou.Admin.sharedPreferenceConfig;

public class indexActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private sharedPreferenceConfig preferenceConfig;
    private int[] images=
            {
                    R.drawable.l1p1g2words,
                    R.drawable.l1p1g2animals,
                    R.drawable.l1p1g1numbers,
                    R.drawable.l1p1g2arrangesentence,
            };
    private recyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new recyclerAdapter(images,this);
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
