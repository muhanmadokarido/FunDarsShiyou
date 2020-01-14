package com.mk_kadish.fundarsshiyou.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mk_kadish.fundarsshiyou.MainActivity;
import com.mk_kadish.fundarsshiyou.R;


public class userManagementActivity extends AppCompatActivity implements HomeFragment.onDbOpListener{
    private sharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            HomeFragment homeFragment=new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,homeFragment).commit();
        }

        preferenceConfig=new sharedPreferenceConfig(getApplicationContext());
    }

    @Override

    public void userPerformed(int id) {
        switch (id) {
            case 0:
                AddUserFragment addUserFragment = new AddUserFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, addUserFragment).addToBackStack(null).commit();
                break;
        }
    }

    public void dBopPerformed(int methode) {

        switch (methode)
        {
            case 0:
                AddSchoolFragment addSchoolFragment=new AddSchoolFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,addSchoolFragment).addToBackStack(null).commit();
            break;
            case 1:
                ReadSchoolsFragment readSchoolsFragment=new ReadSchoolsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,readSchoolsFragment).addToBackStack(null).commit();
                break;
        case 2:
        UpdateSchoolFragment updateSchoolFragment =new UpdateSchoolFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,updateSchoolFragment).addToBackStack(null).commit();
        break;

        case 3:
        DeleteSchoolFragment deleteSchoolFragment=new DeleteSchoolFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,deleteSchoolFragment).addToBackStack(null).commit();
        break;

        default:
            Bundle bundle=new Bundle();
            int i=methode-3;
            bundle.putInt("message",i);
            schoolDetailsFragment schoolDetailsFragment1=new schoolDetailsFragment();
            schoolDetailsFragment1.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,schoolDetailsFragment1).addToBackStack(null).commit();
            break;
        }
    }

    public void logOut(View view)
    {
        preferenceConfig.writeLoginStatus(false);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

