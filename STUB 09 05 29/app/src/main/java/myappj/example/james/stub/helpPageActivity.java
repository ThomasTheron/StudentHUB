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

public class helpPageActivity extends AppCompatActivity {

    ListView myHelpListView;
    String[] hHeadings;
    String[] hbodys;
    String[] hLinks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_page);


        Resources res = getResources();
        myHelpListView = (ListView) findViewById(R.id.helplist);
        hHeadings = res.getStringArray(R.array.listItemhHeading);
        hbodys = res.getStringArray(R.array.listItemhbodys);
        hLinks = res.getStringArray(R.array.listItemshLinks);

        helpitemadapter itemAdapter = new helpitemadapter(this, hHeadings, hbodys, hLinks, ((varStore) getApplication()).getStudentNumber(), ((varStore) getApplication()).getAchivmentsText());
        myHelpListView.setAdapter(itemAdapter);


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
