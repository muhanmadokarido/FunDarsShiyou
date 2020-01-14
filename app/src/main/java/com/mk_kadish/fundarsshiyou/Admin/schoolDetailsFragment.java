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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.mk_kadish.fundarsshiyou.DatabaseRelations.StudentContract;
import com.mk_kadish.fundarsshiyou.R;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class schoolDetailsFragment extends Fragment {
HomeFragment.onDbOpListener dpOplistener;
TextView schoolid_textView;
private Button bn_add_user;
    public schoolDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_school_details, container, false);
        Bundle bundle=getArguments();
        int schoolId= bundle.getInt("message");
        schoolid_textView=view.findViewById(R.id.details_txtview_SchoolId);
        schoolid_textView.setText("School ID: "+schoolId+"");

        bn_add_user=view.findViewById(R.id.btn_addNewStudent);
        bn_add_user.setOnClickListener(btnClick);

        readUsers(view);
        return view;
    }

    //declaring OnClickListener as an object
    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.btn_addNewStudent:
                    dpOplistener.userPerformed(0);
                    break;
            }
        }
    };

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

    private void readUsers(View view)
    {
        SchoolDbHelper schoolDbHelper=new SchoolDbHelper(getActivity());
        SQLiteDatabase database=schoolDbHelper.getReadableDatabase();
        Cursor cursor=schoolDbHelper.readUsers(database);
        String info="";
//        LinearLayout container = (LinearLayout) view.findViewById(R.id.container);
//
//        ConstraintLayout layout = (ConstraintLayout) view.findViewById(R.id.constraintLayout1);
//
//        List<Button> btnList = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String id=Integer.toString(cursor.getInt(cursor.getColumnIndex(StudentContract.studentEntry.user_id)));
            String name=cursor.getString(cursor.getColumnIndex(StudentContract.studentEntry.user_name));
            info=info+"\n\n"+"School ID: "+id+"\n  School Name: "+name;

//            // String anotherButton= "Button"+id;
//            Button anotherButton= new Button(view.getContext());
//            anotherButton.setText(name);
//            anotherButton.setId(Integer.parseInt(id));
//            anotherButton.setOnClickListener(btnClick);
//
//            btnList.add(anotherButton);
//            container.addView(btnList.get(btnList.size() - 1 ));
        }

        schoolDbHelper.close();
    }
}