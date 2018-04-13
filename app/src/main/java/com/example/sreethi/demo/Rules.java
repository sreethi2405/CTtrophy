package com.example.chandan.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

public class Rules extends AppCompatActivity {
        TextView t1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rules);
            getSupportActionBar().hide();
            t1=(TextView)findViewById(R.id.setrules);

            String rules="<b>OFF-FIELD</b>"+"<br/><br/> 1.Admission Fees-550 per team<br/><br/>2.Re-entry allowed only once<br/><br/>3.Knockouts only" +
                    "<br/><br/>4.Maximum strength of a team is 13 per match<br/><br/>5.A Player must play in one team only<br/><br/>6.Inclusion of any player from other departments leads to disqualification of whole team"+
                    "<br/><br/>7.Fixtures will not be changed at any cost untill decided by committee<br/><br/>8.Matches shall be played strictly by the rules and fair-play<br/><br/>9.Committee's decision is final on all matters and arguing teams will be disqualified"+
                    "<br/><br/><b>ON FIELD</b><br/><br/>1.Overs-10(may be reduced or increased based on the situation by the commitee<br/<br/>2.Ball-red(hard)tennis<br/><br/>" +
                    "3.NO powerplayes and freehits<br/><br/>4.5 bowlers can bowl a max. of 2 overs<br/><br/>6.if the team is not present by informed time,the team will be disqualified<br/><br/>7.A bowler can bowl a maximum of 2 bouncers per over(shoulder level)<br/><br/>" +
                    "8.Chucking,worse language and arguments may lead to to the umpires disqualifying a particular player from the match<br/><br/>9.Teams must have scorebooks for all matches<br/><br/>"
                    +"10.Only the Captain/Vice Captain have the right to approach the committee on the field<br/><br/>11.Umpire decision stands at any cost for all matters<br/><br/><b>PRIZE</b><br/><br/>1.The cash prize for Winners and runners will be informed prior to the start of the tournament";
            
            t1.setText(Html.fromHtml(rules));
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
}
