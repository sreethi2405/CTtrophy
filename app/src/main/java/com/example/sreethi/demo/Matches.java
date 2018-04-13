package com.example.chandan.demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Matches extends Fragment {
    TextView t1,t2,t1score,t2score,t1overs,t2overs,t1wickets,t2wickets,info,t[];
    View root;
    DatabaseReference viewlivescore,uploadmatches1,uploadmatches2;
    ArrayList<String> tlist,tscores,tovers,twickets;
    Button upload;
    uploadmatches up;
    FirebaseAuth auth;
    LinearLayout l;
    int i=0;
    public Matches() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_matches, container, false);
        info=(TextView)root.findViewById(R.id.liveinfo);
        l=(LinearLayout)root.findViewById(R.id.layout);
        t1=(TextView)root.findViewById(R.id.team1);
        t2=(TextView)root.findViewById(R.id.team2);
        auth=FirebaseAuth.getInstance();
        upload=(Button)root.findViewById(R.id.uploadmatch);
        t1score=(TextView)root.findViewById(R.id.team1score);
        t2score=(TextView)root.findViewById(R.id.team2score);
        t1wickets=(TextView)root.findViewById(R.id.team1wickets);
        t2wickets=(TextView)root.findViewById(R.id.team2wickets);
        t1overs=(TextView)root.findViewById(R.id.team1overs);
        t2overs=(TextView)root.findViewById(R.id.team2overs);
        t=new TextView[10];
        t[0]=(TextView)root.findViewById(R.id.titlescore1);
        t[1]=(TextView)root.findViewById(R.id.titlescore2);
        t[2]=(TextView)root.findViewById(R.id.titleovers1);
        t[3]=(TextView)root.findViewById(R.id.titleovers2);
        t[4]=(TextView)root.findViewById(R.id.titlewickets);
        t[5]=(TextView)root.findViewById(R.id.titlewickets2);
if(auth.getCurrentUser().getEmail().toString().equalsIgnoreCase("cttrophy2018@gmail.com"))
{
    upload.setVisibility(View.VISIBLE);
}
else
{
    upload.setVisibility(View.INVISIBLE);
}
        viewlivescore = FirebaseDatabase.getInstance().getReference("Live Updates");
        tlist=new ArrayList<String>();
        tovers=new ArrayList<String>();
        twickets=new ArrayList<String>();
        tscores=new ArrayList<String>();
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"hello",Toast.LENGTH_SHORT).show();
                 uploadmatches1=FirebaseDatabase.getInstance().getReference(t1.getText().toString());
                 uploadmatches2=FirebaseDatabase.getInstance().getReference(t2.getText().toString());
                if(Integer.parseInt(t1score.getText().toString())>Integer.parseInt(t2score.getText().toString()))
                {

                    up = new uploadmatches(t1.getText().toString(),t2.getText().toString(),t1score.getText().toString(),t2score.getText().toString(),t1overs.getText().toString(),t2overs.getText().toString(),
                            t1wickets.getText().toString(),t2wickets.getText().toString(),t1.getText().toString()+" Won the match");
                            if(t1.getText().toString().compareTo(t2.getText().toString())>1)
                            {
                                uploadmatches1.child("playedmatches").child(t1.getText().toString() + "vs" + t2.getText().toString()).setValue(up);

                                uploadmatches2.child("playedmatches").child(t1.getText().toString() + "vs" + t2.getText().toString()).setValue(up);
                            }
                            else{
                                uploadmatches1.child("playedmatches").child(t2.getText().toString() + "vs" + t1.getText().toString()).setValue(up);

                                uploadmatches2.child("playedmatches").child(t2.getText().toString() + "vs" + t1.getText().toString()).setValue(up);
                            }
                }
                else
                {
                    up = new uploadmatches(t1.getText().toString(),t2.getText().toString(),t1score.getText().toString(),t2score.getText().toString(),t1overs.getText().toString(),t2overs.getText().toString(),
                            t1wickets.getText().toString(),t2wickets.getText().toString(),t2.getText().toString()+" Won the match");

                    if(t1.getText().toString().compareTo(t2.getText().toString())>1) {
                        uploadmatches1.child("playedmatches").child(t1.getText().toString() + "vs" + t2.getText().toString()).setValue(up);

                        uploadmatches2.child("playedmatches").child(t1.getText().toString() + "vs" + t2.getText().toString()).setValue(up);
                    }
                    else {
                        uploadmatches1.child("playedmatches").child(t2.getText().toString() + "vs" + t1.getText().toString()).setValue(up);

                        uploadmatches2.child("playedmatches").child(t2.getText().toString() + "vs" + t1.getText().toString()).setValue(up);
                    }
                    }
            }
        });
        viewlivescore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                i=0;
                if (dataSnapshot.getChildrenCount() == 2)
                {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        tlist.add(i, snapshot.getKey().toString());

                        tscores.add(i, snapshot.child("score").getValue().toString());
                        tovers.add(i, snapshot.child("overs").getValue().toString());
                        twickets.add(i, snapshot.child("wickets").getValue().toString());
                        i++;
                    }
                    l.setVisibility(View.VISIBLE);
                    t1.setText(tlist.get(0));
                    t2.setText(tlist.get(1));
                    t1score.setText(tscores.get(0));
                    t2score.setText(tscores.get(1));
                    t1overs.setText(tovers.get(0));
                    t2overs.setText(tovers.get(1));
                    t1wickets.setText(twickets.get(0));
                    t2wickets.setText(twickets.get(1));
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t1score.setVisibility(View.VISIBLE);
                    t2score.setVisibility(View.VISIBLE);
                    t1overs.setVisibility(View.VISIBLE);
                    t2overs.setVisibility(View.VISIBLE);
                    t1wickets.setVisibility(View.VISIBLE);
                    t2wickets.setVisibility(View.VISIBLE);
                    info.setVisibility(View.INVISIBLE);
                    for(int i=0;i<=5;i++)
                    {

                        t[i].setVisibility(View.VISIBLE);
                    }

                }


                else if (dataSnapshot.getChildrenCount() == 1) {
                    i=0;

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        tlist.add(i,snapshot.getKey().toString());
                        tlist.add(1, snapshot.child("bowlingteam").getValue().toString());
                        tscores.add(i, snapshot.child("score").getValue().toString());
                        tovers.add(i, snapshot.child("overs").getValue().toString());
                        twickets.add(i, snapshot.child("wickets").getValue().toString());
                        i++;
                    }
                    for(int i=0;i<=5;i++)
                    {
                        t[i].setVisibility(View.VISIBLE);
                    }
                    l.setVisibility(View.VISIBLE);
                    t1.setText(tlist.get(0));
                    t2.setText(tlist.get(1));
                    t1score.setText(tscores.get(0));
                    t2score.setText("0");
                    t1overs.setText(tovers.get(0));
                    t2score.setText("0");
                    t1wickets.setText(twickets.get(0));
                    t2wickets.setText("0");
                    t1.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t1score.setVisibility(View.VISIBLE);
                    t2score.setVisibility(View.VISIBLE);
                    t1overs.setVisibility(View.VISIBLE);
                    t2overs.setVisibility(View.VISIBLE);
                    t1wickets.setVisibility(View.VISIBLE);
                    t2wickets.setVisibility(View.VISIBLE);
                    info.setVisibility(View.INVISIBLE);

                }
                else
                {
                    l.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.INVISIBLE);
                    t2.setVisibility(View.INVISIBLE);
                    t1score.setVisibility(View.INVISIBLE);
                    t2score.setVisibility(View.INVISIBLE);
                    t1overs.setVisibility(View.INVISIBLE);
                    t2overs.setVisibility(View.INVISIBLE);
                    t1wickets.setVisibility(View.INVISIBLE);
                    t2wickets.setVisibility(View.INVISIBLE);
                    info.setVisibility(View.VISIBLE);
                    for(int i=0;i<=5;i++)
                    {
                        t[i].setVisibility(View.INVISIBLE);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return root;
    }

}
