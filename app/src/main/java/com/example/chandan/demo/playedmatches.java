package com.example.chandan.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.text.Line;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class playedmatches extends AppCompatActivity {
DatabaseReference data;
int check;
TextView ti[],t[],sc[],w[],o[],r[],title[];
LinearLayout l1,l2;
ArrayList<String> tname,tovers,twickets,tscores,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playedmatches);
        getSupportActionBar().hide();
        l1=(LinearLayout)findViewById(R.id.layout2);
        l2=(LinearLayout)findViewById(R.id.layout3);
        Intent i =getIntent();
        String bundle=i.getStringExtra("team");
        title=new TextView[6];
        t=new TextView[10];
        ti=new TextView[10];
        sc=new TextView[10];
        w=new TextView[10];
        o=new TextView[10];
        r=new TextView[10];
        title[0]=(TextView)findViewById(R.id.score1);
        title[1]=(TextView)findViewById(R.id.wickets1);
        title[2]=(TextView)findViewById(R.id.overs1);
        title[3]=(TextView)findViewById(R.id.score2);
        title[4]=(TextView)findViewById(R.id.wickets2);
        title[5]=(TextView)findViewById(R.id.overs2);
        t[0]=(TextView)findViewById(R.id.t1name);
        t[1]=(TextView)findViewById(R.id.t2name);
        t[2]=(TextView)findViewById(R.id.t3name);
        t[3]=(TextView)findViewById(R.id.t4name);
        t[4]=(TextView)findViewById(R.id.t5name);
        t[5]=(TextView)findViewById(R.id.t6name);
        sc[0]=(TextView)findViewById(R.id.t1score);
        sc[1]=(TextView)findViewById(R.id.t2score);
        sc[2]=(TextView)findViewById(R.id.t3score);
        sc[3]=(TextView)findViewById(R.id.t4score);
        sc[4]=(TextView)findViewById(R.id.t5score);
        sc[5]=(TextView)findViewById(R.id.t6score);
        w[0]=(TextView)findViewById(R.id.t1wickets);
        w[1]=(TextView)findViewById(R.id.t2wickets);
        w[2]=(TextView)findViewById(R.id.t3wickets);
        w[3]=(TextView)findViewById(R.id.t4wickets);
        w[4]=(TextView)findViewById(R.id.t5wickets);
        w[5]=(TextView)findViewById(R.id.t6wickets);
        o[0]=(TextView)findViewById(R.id.t1overs);
        o[1]=(TextView)findViewById(R.id.t2overs);
        o[2]=(TextView)findViewById(R.id.t3overs);
        o[3]=(TextView)findViewById(R.id.t4overs);
        o[4]=(TextView)findViewById(R.id.t5overs);
        o[5]=(TextView)findViewById(R.id.t6overs);
        r[0]=(TextView)findViewById(R.id.result1);
        r[1]=(TextView)findViewById(R.id.result2);
        r[2]=(TextView)findViewById(R.id.result3);
        ti[0]=(TextView)findViewById(R.id.title1);
        ti[1]=(TextView)findViewById(R.id.title2);
        ti[2]=(TextView)findViewById(R.id.title3);
        data= FirebaseDatabase.getInstance().getReference(bundle);
        check=1;
            tname=new ArrayList<String>();
            tovers=new ArrayList<String>();
            twickets=new ArrayList<String>();
            tscores=new ArrayList<String>();
            result=new ArrayList<String>();
            tname.clear();
            tscores.clear();
            tovers.clear();
            twickets.clear();
            check=1;
            if(data.getKey().toString().equals(bundle))
            {
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (check == 1) {
                            if(dataSnapshot.child("playedmatches").exists()) {
                                for (DataSnapshot dataSnapshot1:dataSnapshot.child("playedmatches").getChildren())
                                {
                                    check=0;
                                tname.add(dataSnapshot1.child("t1").getValue().toString());
                                tovers.add(dataSnapshot1.child("t1overs").getValue().toString());
                                twickets.add(dataSnapshot1.child("t1wickets").getValue().toString());
                                tscores.add(dataSnapshot1.child("t1score").getValue().toString());
                                tname.add(dataSnapshot1.child("t2").getValue().toString());
                                tovers.add(dataSnapshot1.child("t2overs").getValue().toString());
                                twickets.add(dataSnapshot1.child("t2wicets").getValue().toString());
                                tscores.add(dataSnapshot1.child("t2score").getValue().toString());
                                result.add(dataSnapshot1.child("result").getValue().toString());

                                }

                                if(tname.size()==2)
                                {
                                    for(int i=0;i<6;i++) {
                                        title[i].setVisibility(View.VISIBLE);
                                    }
                                    ti[0].setText(tname.get(0).toString()+" vs "+tname.get(1).toString());
                                    r[0].setText(result.get(0).toString());
                                }
                                if(tname.size()==4)
                                {
                                    for(int i=0;i<6;i++) {
                                        title[i].setVisibility(View.VISIBLE);
                                    }
                                    l1.setVisibility(View.VISIBLE);
                                    ti[0].setVisibility(View.VISIBLE);
                                    r[0].setVisibility(View.VISIBLE);
                                    ti[1].setVisibility(View.VISIBLE);
                                    r[1].setVisibility(View.VISIBLE);
                                    ti[0].setText(tname.get(0).toString()+" vs "+tname.get(1).toString());
                                    r[0].setText(result.get(0).toString());
                                    ti[1].setText(tname.get(2).toString()+" vs "+tname.get(3).toString());
                                    r[1].setText(result.get(1).toString());
                                }
                                if(tname.size()==6)
                                {for(int i=0;i<6;i++) {
                                    title[i].setVisibility(View.VISIBLE);
                                }
                                    l1.setVisibility(View.VISIBLE);
                                l2.setVisibility(View.VISIBLE);
                                    ti[0].setVisibility(View.VISIBLE);
                                    r[0].setVisibility(View.VISIBLE);
                                    ti[1].setVisibility(View.VISIBLE);
                                    r[1].setVisibility(View.VISIBLE);
                                    ti[1].setVisibility(View.VISIBLE);
                                    r[1].setVisibility(View.VISIBLE);
                                    ti[0].setText(tname.get(0).toString()+" vs "+tname.get(1).toString());
                                    r[0].setText(result.get(0).toString());
                                    ti[1].setText(tname.get(3).toString()+" vs "+tname.get(4).toString());
                                    r[1].setText(result.get(1).toString());
                                    ti[2].setText(tname.get(4).toString()+" vs "+tname.get(5).toString());
                                    r[2].setText(result.get(2).toString());
                                }
                              for(int i=0;i<tname.size();i++)
                              {
                                  t[i].setText(tname.get(i));
                                  t[i].setVisibility(View.VISIBLE);
                                  sc[i].setText(tscores.get(i));
                                  sc[i].setVisibility(View.VISIBLE);
                                  o[i].setVisibility(View.VISIBLE);
                                  o[i].setText(tovers.get(i));
                                  w[i].setText(twickets.get(i));
                                  w[i].setVisibility(View.VISIBLE);

                              }
                            }
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }


    }
}
