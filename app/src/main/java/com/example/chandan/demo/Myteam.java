package com.example.chandan.demo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class Myteam extends AppCompatActivity {
DatabaseReference myteam,viewplayers;
FirebaseAuth auth;
FirebaseUser user;
String dept[]={"Msc SS 3rd Year","Msc DS 2nd Year","Msc SS 2nd Year","Bsc 2nd Year","Bsc 3rd Year","Msc DS 1st Year","Msc DCS 1st Year","Msc SS 1st Year "};
    Spinner spinner;
    ArrayList<String> plist,skills,blist,bowllist;
    ArrayAdapter<String> at;
    Button b1,b2,b[];
    int check1,check2;
    ImageView i1[];
    String uname[];
    TextView t1;
    StorageReference playerphoto;
    int check3,check4;
int j;
    ProgressDialog Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {  super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myteam);
        getSupportActionBar().hide();

        i1=new ImageView[14];
        check3=1;
        i1[0]=(ImageView)findViewById(R.id.p1p);
        i1[1]=(ImageView)findViewById(R.id.p2p);
        i1[2]=(ImageView)findViewById(R.id.p3p);
        i1[3]=(ImageView)findViewById(R.id.p4p);
        i1[4]=(ImageView)findViewById(R.id.p5p);
        i1[5]=(ImageView)findViewById(R.id.p6p);
        i1[6]=(ImageView)findViewById(R.id.p7p);
        i1[7]=(ImageView)findViewById(R.id.p8p);
        i1[8]=(ImageView)findViewById(R.id.p9p);
        i1[9]=(ImageView)findViewById(R.id.p10p);
        i1[10]=(ImageView)findViewById(R.id.p11p);
        i1[11]=(ImageView)findViewById(R.id.p12p);
        i1[12]=(ImageView)findViewById(R.id.p13p);
        i1[13]=(ImageView)findViewById(R.id.p14p);
        myteam= FirebaseDatabase.getInstance().getReference("MY_Team");
        b1=(Button)findViewById(R.id.saveteam);
        b2=(Button)findViewById(R.id.changeteam);
        t1=(TextView)findViewById(R.id.tname);

        b=new Button[16];
        b[0]=(Button)findViewById(R.id.p1);
        b[1]=(Button)findViewById(R.id.p2);
        b[2]=(Button)findViewById(R.id.p3);
        b[3]=(Button)findViewById(R.id.p4);
        b[4]=(Button)findViewById(R.id.p5);
        b[5]=(Button)findViewById(R.id.p6);
        b[6]=(Button)findViewById(R.id.p7);
        b[7]=(Button)findViewById(R.id.p8);
        b[8]=(Button)findViewById(R.id.p9);
        b[9]=(Button)findViewById(R.id.p10);
        b[10]=(Button)findViewById(R.id.p11);
        b[11]=(Button)findViewById(R.id.p12);
        b[12]=(Button)findViewById(R.id.p13);
        b[13]=(Button)findViewById(R.id.p14);
        plist=new ArrayList<String>();
        skills=new ArrayList<String>();
        blist=new ArrayList<String>();
        bowllist=new ArrayList<String>();
        Dialog=new ProgressDialog(Myteam.this);
        spinner=(Spinner)findViewById(R.id.getmyteam);
        at=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,dept);
        spinner.setAdapter(at);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        playerphoto= FirebaseStorage.getInstance().getReference();

        uname=user.getEmail().split("@");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1=1;
                saveteam();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteteam();
            }
        });
        check2=1;
myteam.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(final DataSnapshot dataSnapshot) {
        if(check2==1) {

            if (dataSnapshot.child(uname[0]).exists())
            {
                spinner.setVisibility(View.INVISIBLE);
                check2=0;
                check3=1;

                viewplayers = FirebaseDatabase.getInstance().getReference(dataSnapshot.child(uname[0]).child("team").getValue().toString());

                viewplayers.addValueEventListener(new ValueEventListener() {
                    @Override

                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        if(check3==1) {

                            if (dataSnapshot1.hasChildren())
                            {int pclose=1;
                                t1.setVisibility(View.VISIBLE);
                                t1.setText(dataSnapshot1.getKey().toString());
                                plist.clear();
                                skills.clear();
                                blist.clear();
                                bowllist.clear();
                                String tnam;
                                for (DataSnapshot dataSnapshot2 : dataSnapshot1.child("players").getChildren()) {
                                    plist.add(dataSnapshot2.child("player_name").getValue().toString());
                                    blist.add(dataSnapshot2.child("btype").getValue().toString());
                                    bowllist.add(dataSnapshot2.child("bowltype").getValue().toString());
                                }

                                for ( j = 0; j < plist.size(); j++) {
                                        String s = "<b>Player Name:</b><br/>"+plist.get(j) +"<br/><br/><b>Batting Type:</b><br/>"+blist.get(j)+"<br/><br/><b>Bowling Type:<b/><br/>"+bowllist.get(j);
                                        b[j].setVisibility(View.VISIBLE);
                                        b[j].setText(Html.fromHtml(s));
                                        i1[j].setVisibility(View.VISIBLE);
                                        playerphoto = FirebaseStorage.getInstance().getReference();
                                        StorageReference filepath = playerphoto.child(spinner.getSelectedItem().toString()).child(plist.get(j));

                                            Glide.with(Myteam.this)
                                                    .using(new FirebaseImageLoader())
                                                    .load(filepath)

                                                    .bitmapTransform(new RoundedCornersTransformation(Myteam.this, 200, 0,
                                                    RoundedCornersTransformation.CornerType.ALL))
                                                    .skipMemoryCache(true)
                                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                                    .into(i1[j]);


                                }

                                int i=plist.size();
                                while(i<14)
                                {
                                    b[i].setVisibility(View.INVISIBLE);
                                    i1[i].setVisibility(View.INVISIBLE);
                                    i++;
                                }
                                check2 = 0;
                                check3=0;

                            }


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
            else
            {

                t1.setText("");
                t1.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.VISIBLE);
                for (int i = 0; i < 13; i++) {
                    b[i].setVisibility(View.INVISIBLE);
                }

            }
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

    }

    private void deleteteam() {
        myteam.removeValue();
        Toast.makeText(getApplicationContext(),"Your Team Removed",Toast.LENGTH_SHORT).show();
        spinner.setVisibility(View.VISIBLE);
        for(int i=0;i<13;i++)
        {
            b[i].setText("");
            b[i].setVisibility(View.INVISIBLE);
            i1[i].setVisibility(View.INVISIBLE);

        }
        t1.setText("");
        t1.setVisibility(View.INVISIBLE);
        check2=0;
    }

    private void saveteam() {
        myteam.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             if(check1==1)
             {

                 if(!dataSnapshot.child(uname[0]).exists())
                 {
                    setteam s=new setteam(spinner.getSelectedItem().toString(),user.getEmail());
                     myteam.child(uname[0]).setValue(s);
                     check1=0;
                     check2=1;
                     spinner.setVisibility(View.INVISIBLE);
                     t1.setVisibility(View.VISIBLE);
                 }
                 else{
                     spinner.setVisibility(View.INVISIBLE);
                     t1.setVisibility(View.VISIBLE);
                     for(int i=0;i<13;i++)
                     {i1[i].setVisibility(View.VISIBLE);
                         b[i].setText("");
                         b[i].setVisibility(View.INVISIBLE);
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
