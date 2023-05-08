package myappj.example.james.stub;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class achivmentsActivity extends AppCompatActivity {

    ListView myAchListView;
    String[] AHeadings;
    String[] Abodys;
    int[] AValues;
    String AchivmentsFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivments);

        AchivmentsFile = ((varStore) getApplication()).getAchivmentsText();


        Resources res = getResources();
        myAchListView = (ListView) findViewById(R.id.achivelist);

        AHeadings = res.getStringArray(R.array.listItemTilesAchHeading);
        Abodys = res.getStringArray(R.array.listItemTilesAchdescription);
        AValues = ((varStore) getApplication()).getAchivmentValues();
        //get the values ferom var store
        AchivmentsFile = ((varStore) getApplication()).getAchivmentsText();
        String[] AchivmentsSplit = AchivmentsFile.split(",");
        AValues = new int[AchivmentsSplit.length];

        for (int i = 1; i < AchivmentsSplit.length; i++) {
            AValues[i - 1] = Integer.parseInt(AchivmentsSplit[i]);

        }

        achitemadapter itemAdapter = new achitemadapter(this, AHeadings, Abodys, AValues);
        myAchListView.setAdapter(itemAdapter);

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
