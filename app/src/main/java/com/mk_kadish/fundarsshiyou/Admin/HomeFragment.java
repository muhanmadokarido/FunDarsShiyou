package com.mk_kadish.fundarsshiyou.Admin;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.mk_kadish.fundarsshiyou.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{
private Button BnSave,BnView,BnDelete,BnUpdate;
onDbOpListener dpOplistener;

public interface  onDbOpListener
{
    public void dBopPerformed(int methode);

    public void userPerformed(int id);
}
    public HomeFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_fragment, container, false);
        BnSave=view.findViewById(R.id.bn_add_School);
        BnSave.setOnClickListener(this);
        BnView=view.findViewById(R.id.bn_view_School);
        BnView.setOnClickListener(this);

        BnUpdate=view.findViewById(R.id.bn_update_School);
        BnUpdate.setOnClickListener(this);
        BnDelete=view.findViewById(R.id.bn_delete_School);
        BnDelete.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    switch (v.getId())
    {
        case R.id.bn_add_School:
            dpOplistener.dBopPerformed(0);
            break;

        case R.id.bn_view_School:
            dpOplistener.dBopPerformed(1);
            break;

        case R.id.bn_update_School:
            dpOplistener.dBopPerformed(2);
            break;

        case R.id.bn_delete_School:
            dpOplistener.dBopPerformed(3);
            break;
    }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity=(Activity) context;
        try
        {
            dpOplistener=(onDbOpListener)  activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString()+"must Implement the interface Method");
        }

    }
}
