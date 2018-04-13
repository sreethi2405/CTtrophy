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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
 Button reg;
 EditText email,pass;
  Button signin;

 ProgressDialog pro;
 FirebaseAuth auth;
CheckBox c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        reg=(Button)findViewById(R.id.register);
        email=(EditText)findViewById(R.id.getemail);
        pass=(EditText)findViewById(R.id.getpassword);
        signin=(Button)findViewById(R.id.signin);
        pro=new ProgressDialog(this);
        if(auth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this, UserProfile.class));
        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this,Loginactivity.class));

            }
        });
        c=(CheckBox)findViewById(R.id.showp);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.isChecked())
                {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
            }
        });
    }
    private void registeruser()
    {
        String emails=email.getText().toString();
        String passs=pass.getText().toString();
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
        pro.setMessage("Registering User");
        pro.show();
        auth.createUserWithEmailAndPassword(emails,passs).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                                pro.dismiss();
                                finish();
                                startActivity(new Intent(MainActivity.this, UserProfile.class));

                        }
                        else
                        {
                            Toast.makeText(MainActivity.this,"Could not Registered Sucessfully",Toast.LENGTH_SHORT).show();
                            pro.cancel();
                        }
                    }
                });

    }
}
