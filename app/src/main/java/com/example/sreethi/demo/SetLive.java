package com.example.chandan.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SetLive extends AppCompatActivity implements View.OnClickListener {
    String dept[]={"Msc SS 3rd Year","Msc DS 2nd Year","Msc SS 2nd Year","Bsc 2nd Year","Bsc 3rd Year","Msc DS 1st Year","Msc DCS 1st Year","Msc SS 1st Year "};
    String overs[] = {"0.0", "0.1", "0.2", "0.3", "0.4", "0.5", "0.6","1.0",
            "1.1", "1.2", "1.3", "1.4", "1.5", "1.6", "2.0", "2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "3.0", "3.1", "3.3", "3.4", "3.5", "3.6",
            "4.0", "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "5.0", "5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "6.0", "6.1", "6.2", "6.3", "6.4", "6.5", "6.6",
            "7.0", "7.1", "7.2", "7.3", "7.4", "7.5", "7.6", "8.0", "8.1", "8.2", "8.3", "8.4", "8.5", "8.6", "9.0", "9.1", "9.2", "9.3", "9.4", "9.5", "9.6",
            "10.0", "10.1", "10.2", "10.3", "10.4", "10.5", "10.6", "11.0", "11.1", "11.2", "11.3", "11.4", "11.5", "11.6", "12.0", "12.1", "12.2", "12.3", "12.4", "12.5", "12.6",
            "13.0", "13.1", "13.2", "13.3", "13.4", "13.5", "13.6", "14.0", "14.1", "14.2", "14.3", "14.4", "14.5", "14.6", "15.0"};
    String wickets[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    Spinner spinner1, spinner2, spinner3, spinner4, spinner5;
    ArrayAdapter<String> at;
    DatabaseReference live;

    EditText e1, e2;
    Button b[], upload, remove;
    ArrayList<String> al;
    int check = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lives);
        getSupportActionBar().hide();


        spinner1 = (Spinner) findViewById(R.id.op1);
        spinner2 = (Spinner) findViewById(R.id.op2);
        spinner3 = (Spinner) findViewById(R.id.batting);
        spinner4 = (Spinner) findViewById(R.id.overs);
        e1 = (EditText) findViewById(R.id.getscore);
        spinner5 = (Spinner) findViewById(R.id.wickets);
        upload = (Button) findViewById(R.id.update);
        remove = (Button) findViewById(R.id.relase);
        b = new Button[17];
        b[1] = (Button) findViewById(R.id.one);
        b[2] = (Button) findViewById(R.id.two);
        b[3] = (Button) findViewById(R.id.three);
        b[4] = (Button) findViewById(R.id.four);
        b[5] = (Button) findViewById(R.id.five);
        b[6] = (Button) findViewById(R.id.six);


        at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dept);
        spinner1.setAdapter(at);
        spinner2.setAdapter(at);
        at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, overs);
        spinner4.setAdapter(at);
        at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, wickets);
        spinner5.setAdapter(at);
        for (int i = 1; i <= 6; i++) {
            b[i].setOnClickListener(this);

        }
        upload.setOnClickListener(this);

        live = FirebaseDatabase.getInstance().getReference("Live Updates");
        al = new ArrayList<String>();
        al.add(spinner1.getSelectedItem().toString());
        al.add(spinner2.getSelectedItem().toString());
        at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, al);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                al = new ArrayList<String>();
                al.add(spinner1.getSelectedItem().toString());
                al.add(spinner2.getSelectedItem().toString());
                at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, al);
                spinner3.setAdapter(at);
                live = FirebaseDatabase.getInstance().getReference("Live Updates");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                al = new ArrayList<String>();
                al.add(spinner1.getSelectedItem().toString());
                al.add(spinner2.getSelectedItem().toString());
                at = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, al);
                spinner3.setAdapter(at);
                live = FirebaseDatabase.getInstance().getReference("Live Updates");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        remove.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void liveupdate() {
        String score = e1.getText().toString();


        if (!TextUtils.isEmpty(score)) {
            if (spinner3.getSelectedItem().toString().equals(spinner1.getSelectedItem().toString())) {
                live.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (check != 1) {
                            if (!dataSnapshot.child(spinner3.getSelectedItem().toString()).exists()) {

                                String id = spinner3.getSelectedItem().toString();
                                livescores l = new livescores(spinner4.getSelectedItem().toString(), spinner3.getSelectedItem().toString(), spinner2.getSelectedItem().toString(), spinner5.getSelectedItem().toString(), e1.getText().toString());
                                live.child(id).setValue(l);
                                Toast.makeText(getApplicationContext(), "Score Updated1", Toast.LENGTH_SHORT).show();
                                check = 1;

                            } else {
                                live.child(spinner3.getSelectedItem().toString()).child("score").setValue(e1.getText().toString());
                                live.child(spinner3.getSelectedItem().toString()).child("wickets").setValue(spinner5.getSelectedItem().toString());
                                live.child(spinner3.getSelectedItem().toString()).child("overs").setValue(spinner4.getSelectedItem().toString());
                                Toast.makeText(getApplicationContext(), "Score Updated1", Toast.LENGTH_SHORT).show();
                                check = 1;
                            }
                        }
                            }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

            }

            if (spinner3.getSelectedItem().toString().equals(spinner2.getSelectedItem().toString())) {
                live.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (check != 1) {
                            if (!dataSnapshot.child(spinner3.getSelectedItem().toString()).exists()) {

                                String id = spinner3.getSelectedItem().toString();
                                livescores l = new livescores(spinner4.getSelectedItem().toString(), spinner3.getSelectedItem().toString(), spinner1.getSelectedItem().toString(), spinner5.getSelectedItem().toString(), e1.getText().toString());
                                live.child(id).setValue(l);
                                Toast.makeText(getApplicationContext(), "Score Updated2", Toast.LENGTH_SHORT).show();
                                check = 1;
                            } else {
                                live.child(spinner3.getSelectedItem().toString()).child("score").setValue(e1.getText().toString());
                                live.child(spinner3.getSelectedItem().toString()).child("wickets").setValue(spinner5.getSelectedItem().toString());
                                live.child(spinner3.getSelectedItem().toString()).child("overs").setValue(spinner4.getSelectedItem().toString());
                                Toast.makeText(getApplicationContext(), "Score Updated2", Toast.LENGTH_SHORT).show();
                                check = 1;
                            }
                        }
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

            }
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter the Score", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.one) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 1));

            } else {
                e1.setText("1");
            }
        }
        if (view.getId() == R.id.two) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 2));

            } else {
                e1.setText("2");
            }
        }
        if (view.getId() == R.id.three) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 3));

            } else {
                e1.setText("3");
            }
        }
        if (view.getId() == R.id.four) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 4));

            } else {
                e1.setText("4");
            }
        }
        if (view.getId() == R.id.five) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 5));

            } else {
                e1.setText("5");
            }
        }
        if (view.getId() == R.id.six) {
            if (!e1.getText().toString().equals("")) {
                e1.setText(String.valueOf(Integer.parseInt(e1.getText().toString()) + 6));

            } else {
                e1.setText("6");
            }
        }
        if (view.getId() == R.id.update) {
            check=0;
            liveupdate();

        }
        if (view.getId() == R.id.relase) {

            live.removeValue();
            if(live.getKey().toString().equals("Live Updates")) {
                check = 1;

            }
            else {
                Toast.makeText(getApplicationContext(), live.getKey().toString() + " Removed", Toast.LENGTH_SHORT).show();
                check = 0;
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("destry", "onDestroy: destroy" );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("start","start");
    }
}