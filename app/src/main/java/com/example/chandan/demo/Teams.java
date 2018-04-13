package com.example.chandan.demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class Teams extends Fragment {

ListView li;
View rootView;
    DatabaseReference teams;
    ArrayAdapter<String> at;
    String dept[]={"Msc SS 3rd Year","Msc DS 2nd Year","Msc SS 2nd Year","Bsc 2nd Year","Bsc 3rd Year","Msc DS 1st Year","Msc DCS 1st Year","Msc SS 1st Year "};
        public Teams() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_teams, container, false);
        li=(ListView)rootView.findViewById(R.id.teamlistview);
       at=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,dept);
       li.setAdapter(at);

li.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(), playedmatches.class);

        intent.putExtra("team", li.getItemAtPosition((int) id).toString());
        startActivity(intent);
    }
});
return rootView;

    }

}
