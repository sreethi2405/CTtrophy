package com.example.chandan.demo;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chandan on 31-Dec-17.
 */

public class playerslist extends ArrayAdapter<playerdetails> {
    private Activity context;
    private List<playerdetails> playersList;

    public playerslist(Activity context,List<playerdetails> playersList) {
        super(context, R.layout.listlayout,playersList);
        this.playersList = playersList;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.listlayout,null,true);

        TextView textViewplname=(TextView)listViewItem.findViewById(R.id.TextViewname);
        TextView textViewplskill=(TextView)listViewItem.findViewById(R.id.TextViewskills);
        playerdetails p1=playersList.get(position);

        textViewplname.setText(p1.getPlayer_name());
        textViewplskill.setText(p1.getSkill());
        return listViewItem;
    }
}
