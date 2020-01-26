package com.mk_kadish.fundarsshiyou;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mk_kadish.fundarsshiyou.Admin.HomeActivity;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolDbHelper;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.StudentContract;
import com.mk_kadish.fundarsshiyou.Admin.sharedPreferenceConfig;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.UserKeyValue;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private sharedPreferenceConfig preferenceConfig;
    EditText UserName, UserPassword;
    private int userKeyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceConfig = new sharedPreferenceConfig(getApplicationContext());
        UserName = findViewById(R.id.user_name);
        UserPassword = findViewById(R.id.user_password);

        if (preferenceConfig.readLoginStatus()) {

            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }

        if (preferenceConfig.readStudentLoginStatus()) {

            startActivity(new Intent(this, althaniActivity.class));
            finish();
        }
    }

    public void loginUser(View view) {

        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        if (name.equals(getResources().getString(R.string.user_name)) && password.equals(getResources().getString(R.string.user_password)))
        {
            startActivity(new Intent(this, HomeActivity.class));
            preferenceConfig.writeLoginStatus(true);
            finish();
        }
        else
            {
            boolean Authorized=false;
            ArrayList<UserKeyValue> lst= readUsers(view);
            for(int i=0;i<lst.size();i++)
            {
               UserKeyValue t= (UserKeyValue)lst.get(i);
               String userName1=t.key;
               String password1=t.value;
               if(userName1.equals(name) && password.equals(password1))
               {
                   Authorized=true;
                   break;
               }
            }
            boolean mawjuud=false;

            if((Authorized) || (name.equals("user") && password.equals("123456")))
            {
                preferenceConfig.writeStudentLoginStatus(true);
                startActivity(new Intent(this, althaniActivity.class));
                finish();
                SchoolDbHelper schoolDbHelper=new SchoolDbHelper(this);
                SQLiteDatabase database=schoolDbHelper.getReadableDatabase();
                Cursor cursor=schoolDbHelper.readUsers(database);
                while(cursor.moveToNext())
                {
                    int id =cursor.getInt(cursor.getColumnIndex(StudentContract.studentEntry.user_id));
                    if(id==11)
                        mawjuud=true;
                }
                if(!mawjuud)
                schoolDbHelper.addUser(11,"user11",database);
                mawjuud=false;
                schoolDbHelper.close();
            }
            else
            {
                Toast.makeText(this, "بياناتك غير صحيحة .... حاول مرة أخرى ", Toast.LENGTH_SHORT).show();
                UserName.setText("");
                UserPassword.setText("");
            }
        }
    }

    private ArrayList<UserKeyValue> readUsers(View view) {
        ArrayList<UserKeyValue> userKeyValue = new ArrayList<>();
        SchoolDbHelper schoolDbHelper = new SchoolDbHelper(this);
        SQLiteDatabase database = schoolDbHelper.getReadableDatabase();
        Cursor cursor = schoolDbHelper.readUsers(database);
        String info = "";
        while (cursor.moveToNext()) {
            String userNamex = cursor.getString(cursor.getColumnIndex(StudentContract.studentEntry.user_name));
            String passwordx = cursor.getString(cursor.getColumnIndex(StudentContract.studentEntry.user_password));
            userKeyValue.add(new UserKeyValue(userNamex, passwordx));
        }
        return  userKeyValue;
    }
}
