package com.mk_kadish.fundarsshiyou.Admin;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.mk_kadish.fundarsshiyou.R;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolContract;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolDbHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadSchoolsFragment extends Fragment {
//private TextView textView;
HomeFragment.onDbOpListener dpOplistener;
    //declaring OnClickListener as an object
    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int id= v.getId();
            id=id+3;
            dpOplistener.dBopPerformed(id);
        }
    };


    public ReadSchoolsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_read_schools, container, false);
        List<Button> btnList = new ArrayList<>();
        readSchools(view);
        return view;
    }

    private void readSchools(View view)
    {
        SchoolDbHelper schoolDbHelper=new SchoolDbHelper(getActivity());
        SQLiteDatabase database=schoolDbHelper.getReadableDatabase();
        Cursor cursor=schoolDbHelper.readSchool(database);
        String info="";
        LinearLayout container = (LinearLayout) view.findViewById(R.id.container);

        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.constraintLayout1);

        List<Button> btnList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String id=Integer.toString(cursor.getInt(cursor.getColumnIndex(SchoolContract.SchoolEntry.school_id)));
            String name=cursor.getString(cursor.getColumnIndex(SchoolContract.SchoolEntry.school_name));
            info=info+"\n\n"+"School ID: "+id+"\n  School Name: "+name;

           // String anotherButton= "Button"+id;
            Button anotherButton= new Button(view.getContext());
            anotherButton.setText(name);
            anotherButton.setId(Integer.parseInt(id));
            anotherButton.setOnClickListener(btnClick);

            btnList.add(anotherButton);
            container.addView(btnList.get(btnList.size() - 1 ));
        }
       //layout.addView(btnList.get(btnList.size() - 1 ));


       //textView.setText(info);

        schoolDbHelper.close();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity=(Activity) context;
        try
        {
            dpOplistener=(HomeFragment.onDbOpListener)  activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+"must Implement the interface Method");
        }

    }
}
