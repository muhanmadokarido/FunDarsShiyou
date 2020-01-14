package com.mk_kadish.fundarsshiyou.Admin;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.mk_kadish.fundarsshiyou.R;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolDbHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteSchoolFragment extends Fragment {

    private Button bn_delete;
    private EditText txt_id;

    public DeleteSchoolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_delete_school, container, false);
        bn_delete = view.findViewById(R.id.delete_bn_save);
        txt_id = view.findViewById(R.id.delete_txt_school_id);
        bn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSchool();
            }
        });
        return view;
    }
    private void deleteSchool()
    {
        int id= Integer.parseInt(txt_id.getText().toString());

        SchoolDbHelper schoolDbHelper=new SchoolDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase=schoolDbHelper.getWritableDatabase();
        schoolDbHelper.deleteSchool(id,sqLiteDatabase);
        schoolDbHelper.close();

        txt_id.setText("");
        Toast.makeText(getActivity(),"School Deleted successfully",Toast.LENGTH_LONG);

    }

}
