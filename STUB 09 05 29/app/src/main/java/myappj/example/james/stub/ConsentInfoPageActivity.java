package myappj.example.james.stub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class ConsentInfoPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consent_info_page);

        Button agreebtn = (Button) findViewById(R.id.agreebutton);
        agreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((varStore)getApplication()).setGivenConsent(true);

                //by creating it for first time
                Object[] userNameNFile= new Object[2];

                userNameNFile[0]= ((varStore)getApplication()).getStudentNumber();
                String AchivmentsFile=((varStore)getApplication()).getAchivmentsText();

                String[] AchivmentsSplit=AchivmentsFile.split(",");

                int[] AValues=new int[AchivmentsSplit.length];

                for(int i=1; i<AchivmentsSplit.length;i++){
                    AValues[i]= Integer.parseInt(AchivmentsSplit[i]);
                }
                //update NO help clicks
                userNameNFile[1]="start,true,"+AValues[1]+","+AValues[2]+","+AValues[3]+","+AValues[4]+ ",end";


                achivmentsUpdaterNetwork task = new achivmentsUpdaterNetwork();
                Object ans = null;
                try {
                    ans = task.execute(userNameNFile).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }





                Toast.makeText(getApplicationContext(),"Thank you", Toast.LENGTH_LONG).show();
                Intent startIntent = new Intent(getApplicationContext(), DashBordActivity.class);
                startIntent.putExtra("courseCode", getResources().getString(R.string.course1));
                startActivity(startIntent);




            }
        });
        Button disagreebtn = (Button) findViewById(R.id.nobtn);
        disagreebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"Thank you for considering", Toast.LENGTH_LONG).show();
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });

        String AchivmentsFile=(String)((varStore)getApplication()).getAchivmentsText();;//storeing here to check if agreed to consent form or not

        if( ((varStore)getApplication()).getGivenConsent()==true){// if students has already agreed
            agreebtn.setEnabled(false);
            disagreebtn.setEnabled(false);
        }


    }


    //puts main_screan_selection menue(3dots) into the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //only shows menu if they have given consent.
        if( ((varStore)getApplication()).getGivenConsent()==true) {// if students has already agreed

            MenuInflater inflater = getMenuInflater();
             inflater.inflate(R.menu.menu_screan_selection, menu);
        }
        return true;
    }

    //handles item selection actions. ie. "If menu item pressed what will happen"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuForAllActivitys menueHandler= new menuForAllActivitys(this,item);

        if( menueHandler.JonOptionsItemSelected(item)){
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

}
