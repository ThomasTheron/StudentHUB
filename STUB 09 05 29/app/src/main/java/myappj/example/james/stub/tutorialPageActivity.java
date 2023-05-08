package myappj.example.james.stub;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class tutorialPageActivity extends AppCompatActivity {

    ListView mytutlistview;
    String[] tutorialMainInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_page);

        Resources res = getResources();
        final String tutSignUpLinkAndress = res.getString(R.string.tutSignUpLink);
        Button linkbtn = (Button) findViewById(R.id.TutSignUpBtn);
        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriadress = Uri.parse(tutSignUpLinkAndress);
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uriadress);
                if (openBrowser.resolveActivity(getPackageManager()) != null) {

                    Object[] userNameNFile = new Object[2];
                    userNameNFile[0] = ((varStore) getApplication()).getStudentNumber();
                    String AchivmentsFile = ((varStore) getApplication()).getAchivmentsText();
                    String[] AchivmentsSplit = AchivmentsFile.split(",");

                    int[] AValues = new int[AchivmentsSplit.length];

                    for (int i = 1; i < AchivmentsSplit.length; i++) {
                        AValues[i] = Integer.parseInt(AchivmentsSplit[i]);

                    }
                    AValues[3] = AValues[3] + 1;
                    userNameNFile[1] = "start,true," + AValues[1] + "," + AValues[2] + "," + AValues[3] + "," + AValues[4] + ",end";//todo update when more values come in
                    achivmentsUpdaterNetwork task2 = new achivmentsUpdaterNetwork();
                    Object ans2 = null;
                    try {
                        ans2 = task2.execute(userNameNFile).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    startActivity(openBrowser);
                } else {
                    System.out.println("problem");
                }
            }
        });


        tutorialMainInfo = ((String) ((varStore) getApplication()).gettutorialMain()).split("\n");

    }

    //puts main_screan_selection menue(3dots) into the main activity
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_screan_selection, menu);
        return true;
    }

    //handles item selection actions. ie. "If menu item pressed what will happen"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        menuForAllActivitys menueHandler = new menuForAllActivitys(this, item);

        if (menueHandler.JonOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

}
