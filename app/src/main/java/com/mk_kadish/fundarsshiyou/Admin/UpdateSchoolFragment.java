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
public class UpdateSchoolFragment extends Fragment {

    private Button bn_save;
    private EditText txt_id, txt_name;

    public UpdateSchoolFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_school, container, false);

        bn_save = view.findViewById(R.id.edit_bn_save);
        txt_id = view.findViewById(R.id.edit_txt_school_id);
        txt_name = view.findViewById(R.id.edit_txt_school_name);

        bn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSchool();
                    }
        });
        return view;
    }
    private void updateSchool()
    {
        int id= Integer.parseInt(txt_id.getText().toString());
        String name=txt_name.getText().toString();

        SchoolDbHelper schoolDbHelper=new SchoolDbHelper(getActivity());
        SQLiteDatabase sqLiteDatabase=schoolDbHelper.getWritableDatabase();
        schoolDbHelper.updateSchool(id,name,sqLiteDatabase);
        schoolDbHelper.close();

        txt_id.setText("");
        txt_name.setText("");
        Toast.makeText(getActivity(),"School updated successfully",Toast.LENGTH_SHORT);

    }
}