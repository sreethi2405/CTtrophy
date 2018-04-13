package com.example.chandan.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SetFixtures extends AppCompatActivity {
    String dept[]={"Msc SS 3rd Year","Msc DS 2nd Year","Msc SS 2nd Year","Bsc 2nd Year","Bsc 3rd Year","Msc DS 1st Year","Msc DCS 1st Year","Msc SS 1st Year "};
    String title[]={"LEAGUE","SEMI FINALS","FINALS"};
    Spinner spinner1, spinner2,spinner3;
    ArrayAdapter<String> at;
    ArrayList<String> al;
    Button b1,b2,b3;
    int check,check1,check3;
    DatabaseReference Fixtures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_fixtures);
        getSupportActionBar().hide();
        spinner1 = (Spinner) findViewById(R.id.fixturet1);
        spinner2 = (Spinner) findViewById(R.id.fixturet2);
        spinner3=(Spinner)findViewById(R.id.fixturetitle);
        Fixtures= FirebaseDatabase.getInstance().getReference("Fixtures");
        b1=(Button)findViewById(R.id.setfix);
        b2=(Button)findViewById(R.id.delfix);
        b3=(Button)findViewById(R.id.deleteall);
at= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dept);
        setspinner(spinner1);
        setspinner(spinner2);
        at= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,title);
        spinner3.setAdapter(at);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=1;
                Fixtures.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(check==1) {
                            if (spinner1.getSelectedItem().toString().equals(spinner2.getSelectedItem().toString())) {
                                Toast.makeText(getApplicationContext(), "Same Team selected", Toast.LENGTH_SHORT).show();
                            } else if (dataSnapshot.getChildrenCount() < 5) {
                                if (!dataSnapshot.child(spinner1.getSelectedItem().toString() + "vs" + spinner2.getSelectedItem().toString()).exists()) {
                                    String team1 = spinner1.getSelectedItem().toString();
                                    String team2 = spinner2.getSelectedItem().toString();
                                    String title1 = spinner3.getSelectedItem().toString();
                                    teamfixtures f1 = new teamfixtures(team1, team2, title1);
                                    Fixtures.child(spinner1.getSelectedItem().toString() + "vs" + spinner2.getSelectedItem().toString()).setValue(f1);
                                    check=0;
                                } else {
                                    Toast.makeText(getApplicationContext(), "Fixtures Already Exists", Toast.LENGTH_SHORT).show();
                                }
                            } else if (dataSnapshot.getChildrenCount() >= 5) {
                                Toast.makeText(getApplicationContext(), "Maximum Fixture Limit Reached", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1=1;
                Fixtures.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(check1==1) {
                            if (dataSnapshot.child(spinner1.getSelectedItem().toString() + "vs" + spinner2.getSelectedItem().toString()).exists()) {
                                Fixtures.child(spinner1.getSelectedItem().toString() + "vs" + spinner2.getSelectedItem().toString()).removeValue();
                                check = 0;
                                check1=0;
                            } else {
                                Toast.makeText(getApplicationContext(), "Fixtures Not Exists", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check3=1;
                Fixtures.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(check3==1)
                        {
                            if(dataSnapshot.getKey().equals("Fixtures"))
                            {


                                Fixtures.getRef().removeValue();
                                check3=0;
                                check1=0;
                                check=0;
                                Toast.makeText(getApplicationContext(),"Fixtures removed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void setspinner(Spinner spin) {
        spin.setAdapter(at);

    }

}
