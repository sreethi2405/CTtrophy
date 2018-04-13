package com.example.chandan.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class AddTeams extends AppCompatActivity {
    String skil[]={"Batsman","Bowler","All Rounder"};
    String dept[]={"Msc SS 3rd Year","Msc DS 2nd Year","Msc SS 2nd Year","Bsc 2nd Year","Bsc 3rd Year","Msc DS 1st Year","Msc DCS 1st Year","Msc SS 1st Year "};
    String bat[]={"Right Handed Batsman","Left Handed Batsmen"};
    String bowl[]={"None","Left Hand seam","Left Hand off spin","Left hand leg spin","Left Hand slow medium","Right Hand off spin","Right hand leg spin","Right Hand slow medium","Right Hand seam"};
    Spinner spinner,spinner1,spinner2,spinner3;
ArrayAdapter<String> at;
DatabaseReference player;
EditText e1,e2;
Button b1,b2,b3;
List <playerdetails> listplayerlist;
ListView listViewplayers;
int check1,check2,check3=0;
TextView t1;
Uri image;
int check4;
StorageReference playerphoto;
ProgressDialog progressDialog;
    Bitmap bmp;
    private static final int PICK_GALLERY_IMAGE = 1;
    Intent i;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teams);
        getSupportActionBar().hide();
        setTitle("AddTeam");
progressDialog=new ProgressDialog(this);
        listViewplayers=(ListView)findViewById(R.id.ListViewPlayer);
        playerphoto= FirebaseStorage.getInstance().getReference();
        listplayerlist=new ArrayList<playerdetails>();
        t1=(TextView)findViewById(R.id.imageinfo);
    t1.setText("");
        spinner=(Spinner)findViewById(R.id.spinner);
    spinner2=(Spinner)findViewById(R.id.spinner2);
    spinner3=(Spinner)findViewById(R.id.spinner3);
        at=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,dept);
        spinner.setAdapter(at);
        spinner1=(Spinner)findViewById(R.id.spinner1);
        at=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,skil);
        spinner1.setAdapter(at);
    at=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,bat);
    spinner2.setAdapter(at);
    at=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,bowl);
    spinner3.setAdapter(at);
        e1=(EditText)findViewById(R.id.getplayername);
        e2=(EditText)findViewById(R.id.getplayeremail);
        b1=(Button)findViewById(R.id.join);
        b2=(Button)findViewById(R.id.uploadimage);
        b3=(Button)findViewById(R.id.delete);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,PICK_GALLERY_IMAGE);
            }
        });

    player=FirebaseDatabase.getInstance().getReference(spinner.getSelectedItem().toString());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addplayer();
            }
        });
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            check2 = 1;
            player=FirebaseDatabase.getInstance().getReference(spinner.getSelectedItem().toString());
            player.addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                 if(check2==1) {

                         if (dataSnapshot.getKey().toString().equals(spinner.getSelectedItem().toString())) {

                             listplayerlist.clear();

                             for (DataSnapshot playersnapshot : dataSnapshot.child("players").getChildren()) {
                                 if (playersnapshot.hasChildren()) {
                                     String pname, pemail, skill, tname, bat, bowl;
                                     pname = playersnapshot.child("player_name").getValue().toString();
                                     tname = playersnapshot.child("team_name").getValue().toString();
                                     pemail = playersnapshot.child("email").getValue().toString();
                                     skill = playersnapshot.child("skill").getValue().toString();
                                     bat = playersnapshot.child("btype").getValue().toString();
                                     bowl = playersnapshot.child("bowltype").getValue().toString();
                                     playerdetails p2 = new playerdetails(tname, pname, pemail, skill, bat, bowl);
                                     listplayerlist.add(p2);
                                 }
                             }
                             playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                             listViewplayers.setAdapter(adapter);
                         } else {
                             listplayerlist.clear();
                             playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                             listViewplayers.setAdapter(adapter);
                         }

                 }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),databaseError.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 check4=1;
                player.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {
                        if (check4 == 1) {
                            for (final DataSnapshot dataSnapshot1 : dataSnapshot.child("players").getChildren())
                            {
                                if (dataSnapshot1.child("player_name").getValue().toString().equals(e1.getText().toString())) {
                                    StorageReference filepath = playerphoto.child(spinner.getSelectedItem().toString()).child(e1.getText().toString());
                                    Toast.makeText(getApplicationContext(),playerphoto.child(spinner.getSelectedItem().toString()).child(e1.getText().toString()).toString(), Toast.LENGTH_SHORT).show();
                                    filepath.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            dataSnapshot1.getRef().removeValue();
                                            Toast.makeText(getApplicationContext(),"Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    check4=0;
                                    if(dataSnapshot1.hasChildren()) {
                                        check3=1;
                                    }
                                    else
                                    {
                                        listplayerlist.clear();
                                        playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                                        listViewplayers.setAdapter(adapter);
                                    }

                                }
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

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });
    check3=0;
            player.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (check3 == 1) {

                    if (dataSnapshot.getKey().toString().equals(spinner.getSelectedItem().toString())) {
                        check3 = 0;
                        listplayerlist.clear();

                        for (DataSnapshot playersnapshot : dataSnapshot.child("players").getChildren()) {
                            if (playersnapshot.hasChildren()) {
                                String pname, pemail, skill, tname,bat,bowl;
                                pname = playersnapshot.child("player_name").getValue().toString();
                                tname = playersnapshot.child("team_name").getValue().toString();
                                pemail = playersnapshot.child("email").getValue().toString();
                                skill = playersnapshot.child("skill").getValue().toString();
                                bat= playersnapshot.child("btype").getValue().toString();
                                bowl = playersnapshot.child("bowltype").getValue().toString();
                                playerdetails p2 = new playerdetails(tname, pname, pemail, skill,bat,bowl);
                                listplayerlist.add(p2);
                            }
                        }
                        playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                        listViewplayers.setAdapter(adapter);
                    } else {
                        listplayerlist.clear();
                        playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                        listViewplayers.setAdapter(adapter);
                        check3=0;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();




    }

    public void  addplayer()
    {
        check1=1;
      String pname=e1.getText().toString();
      String pemail=e2.getText().toString();
      if(!TextUtils.isEmpty(pname)&&!TextUtils.isEmpty(pemail)&&!TextUtils.isEmpty(t1.getText().toString()))
      {

    player.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (check1 == 1) {
                if (dataSnapshot.child("players").getChildrenCount() < 13) {
                    String id=player.push().getKey().toString();
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    playerdetails p1 = new playerdetails(spinner.getSelectedItem().toString(), e1.getText().toString(), e2.getText().toString(), spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),spinner3.getSelectedItem().toString());
                    player.child("players").child(id).setValue(p1);
                    progressDialog.setMessage("Player uploading...");
                    progressDialog.show();
                    StorageReference filepath=playerphoto.child(spinner.getSelectedItem().toString()).child(e1.getText().toString());
                    filepath.putBytes(byteArray).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            t1.setText("");
                        }
                    });
                    Toast.makeText(getApplication(),"player Added",Toast.LENGTH_SHORT).show();
                    listplayerlist.clear();

                    for (DataSnapshot playersnapshot : dataSnapshot.child("players").getChildren()) {
                        String pname, pemail, skill, tname,bat,bowl;
                        pname = playersnapshot.child("player_name").getValue().toString();
                        tname = playersnapshot.child("team_name").getValue().toString();
                        pemail = playersnapshot.child("email").getValue().toString();
                        skill = playersnapshot.child("skill").getValue().toString();
                        bat= playersnapshot.child("btype").getValue().toString();
                        bowl = playersnapshot.child("bowltype").getValue().toString();
                        playerdetails p2 = new playerdetails(tname, pname, pemail, skill,bat,bowl);
                        listplayerlist.add(p2);
                    }
                    playerslist adapter = new playerslist(AddTeams.this, listplayerlist);
                    listViewplayers.setAdapter(adapter);
                    check1=0;
                    check3=1;
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Maximum 13 players in a team",Toast.LENGTH_SHORT).show();
                    check1=0;
                }
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();
        }
    });

      }
      else
      {
          Toast.makeText(getApplicationContext(),"Please Enter Name ,Email and upload photo ",Toast.LENGTH_SHORT).show();
      }
  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_GALLERY_IMAGE&&data!=null&&resultCode==RESULT_OK) {
            image = data.getData();
            InputStream image_stream = null;
            try {
                image_stream = getContentResolver().openInputStream(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Bitmap bitmap = BitmapFactory.decodeStream(image_stream);
            bmp = Bitmap.createScaledBitmap(bitmap, 350, 350, true);

            t1.setText(image.getPath().toString());


            }
        }


    }







