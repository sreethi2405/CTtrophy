package com.example.chandan.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginactivity extends AppCompatActivity {
EditText editTextpass,edittextmail;
Button Buttonlogin;
Button bu;
ProgressDialog pro;
CheckBox c;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        getSupportActionBar().hide();
        edittextmail=(EditText)findViewById(R.id.getuseremail);
        editTextpass=(EditText)findViewById(R.id.getuserpassword);
        Buttonlogin=(Button)findViewById(R.id.signup);
        bu=(Button)findViewById(R.id.login);
        pro=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(Loginactivity.this,UserProfile.class));
        }

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();

            }
        });
        Buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Loginactivity.this,MainActivity.class));

            }
        });
        c=(CheckBox)findViewById(R.id.ushow);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.isChecked())
                {
                    editTextpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    editTextpass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });
    }
    private void loginuser()
    {
        String emails=edittextmail.getText().toString();
        String passs=editTextpass.getText().toString();
        if(TextUtils.isEmpty(emails))
        {
            Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(passs))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        pro.setMessage("Please Wait...Logging in");
        pro.show();

        auth.signInWithEmailAndPassword(emails,passs).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pro.dismiss();
                if(task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(Loginactivity.this, UserProfile.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Register Your Account",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
