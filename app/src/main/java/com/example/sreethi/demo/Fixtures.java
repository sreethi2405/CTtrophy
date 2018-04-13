package com.example.chandan.demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fixtures extends Fragment {
    TextView t1, t2;
    Button b[];
    View root;
    DatabaseReference viewfixtureslist;
    ArrayList<String> tlist1, tlist2, title;
    int i = 0;

    public Fixtures() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        root = inflater.inflate(R.layout.fragment_fixtures, container, false);
        b = new Button[10];
        t1 = (TextView) root.findViewById(R.id.Title);
        t2 = root.findViewById(R.id.finfo);
        b[0] = (Button) root.findViewById(R.id.ft1);
        b[1] = (Button) root.findViewById(R.id.ft2);
        b[2] = (Button) root.findViewById(R.id.ft3);
        b[3] = (Button) root.findViewById(R.id.ft4);
        b[4] = (Button) root.findViewById(R.id.ft5);
        b[5] = (Button) root.findViewById(R.id.ft6);
        b[6] = (Button) root.findViewById(R.id.ft7);
        b[7] = (Button) root.findViewById(R.id.ft8);
        b[8] = (Button) root.findViewById(R.id.ft9);
        b[9] = (Button) root.findViewById(R.id.ft10);
        tlist1 = new ArrayList<String>();
        tlist2 = new ArrayList<String>();
        title = new ArrayList<String>();
        viewfixtureslist = FirebaseDatabase.getInstance().getReference("Fixtures");
        viewfixtureslist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                i=0;
                if(dataSnapshot.getChildrenCount()==1) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        tlist1.add(dataSnapshot1.child("team1").getValue().toString());
                        tlist2.add(dataSnapshot1.child("team2").getValue().toString());
                        title.add(dataSnapshot1.child("title").getValue().toString());
                    }
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText(title.get(0));
                    for(int i=0;i<2;i++) {
                        b[i].setVisibility(View.VISIBLE);
                    }
                    b[0].setText(tlist1.get(0));
                    b[1].setText(tlist2.get(0));
                }
                if(dataSnapshot.getChildrenCount()==2) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        tlist1.add(dataSnapshot1.child("team1").getValue().toString());
                        tlist2.add(dataSnapshot1.child("team2").getValue().toString());
                        title.add(dataSnapshot1.child("title").getValue().toString());
                    }
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText(title.get(0));
                    for(int i=0;i<4;i++) {
                        b[i].setVisibility(View.VISIBLE);

                    }
                    b[0].setText(tlist1.get(0));
                    b[1].setText(tlist2.get(0));
                    b[2].setText(tlist1.get(1));
                    b[3].setText(tlist2.get(1));
                }
                if(dataSnapshot.getChildrenCount()==3) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        tlist1.add(dataSnapshot1.child("team1").getValue().toString());
                        tlist2.add(dataSnapshot1.child("team2").getValue().toString());
                        title.add(dataSnapshot1.child("title").getValue().toString());
                    }
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText(title.get(0));
                    for(int i=0;i<6;i++) {
                        b[i].setVisibility(View.VISIBLE);
                    }
                    b[0].setText(tlist1.get(0));
                    b[1].setText(tlist2.get(0));
                    b[2].setText(tlist1.get(1));
                    b[3].setText(tlist2.get(1));
                    b[4].setText(tlist1.get(2));
                    b[5].setText(tlist2.get(2));

                }
                if(dataSnapshot.getChildrenCount()==4) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        tlist1.add(dataSnapshot1.child("team1").getValue().toString());
                        tlist2.add(dataSnapshot1.child("team2").getValue().toString());
                        title.add(dataSnapshot1.child("title").getValue().toString());
                    }
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText(title.get(0));
                    for(int i=0;i<8;i++) {
                        b[i].setVisibility(View.VISIBLE);

                    }

                    b[0].setText(tlist1.get(0));
                    b[1].setText(tlist2.get(0));
                    b[2].setText(tlist1.get(1));
                    b[3].setText(tlist2.get(1));
                    b[4].setText(tlist1.get(2));
                    b[5].setText(tlist2.get(2));
                    b[6].setText(tlist1.get(3));
                    b[7].setText(tlist2.get(3));
                }
                if(dataSnapshot.getChildrenCount()==5) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                    {
                        tlist1.add(dataSnapshot1.child("team1").getValue().toString());
                        tlist2.add(dataSnapshot1.child("team2").getValue().toString());
                        title.add(dataSnapshot1.child("title").getValue().toString());
                    }
                    t2.setVisibility(View.INVISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    t1.setText(title.get(0));
                    for(int i=0;i<10;i++) {
                        b[i].setVisibility(View.VISIBLE);

                    }
                    b[0].setText(tlist1.get(0));
                    b[1].setText(tlist2.get(0));
                    b[2].setText(tlist1.get(1));
                    b[3].setText(tlist2.get(1));
                    b[4].setText(tlist1.get(2));
                    b[5].setText(tlist2.get(2));
                    b[6].setText(tlist1.get(3));
                    b[7].setText(tlist2.get(3));
                    b[8].setText(tlist1.get(4));
                    b[9].setText(tlist2.get(4));

                }
                if(dataSnapshot.getChildrenCount()<=0)
                {
                    t2.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.INVISIBLE);
                    for(int i=0;i<10;i++) {
                        b[i].setVisibility(View.INVISIBLE);

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
