package myappj.example.james.stub;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DashBordActivity extends AppCompatActivity {
    //https://www.youtube.com/watch?v=6ow3L39Wxmg

    //   Toast.makeText(this,"1", Toast.LENGTH_LONG).show();
//getApplicationContext()
    String course;
    double[] Coursewaiting;//exam/test//assinments
    int[][] courseMarks;

    ListView MainListView;
    String[] listItemHeadings;
    int[][] percantagesIntArray;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        res = getResources();

        // find out wich course data to display
        course = getResources().getString(R.string.course1);
        TextView mainMenuetxt1 = (TextView) findViewById(R.id.dashCourseCodetxt);
        if (getIntent().hasExtra("courseCode")) {//checks what course should be shown
            course = getIntent().getExtras().getString("courseCode");
        }
        //find out course weighting and marks;
        Coursewaiting = new double[3];
        courseMarks = new int[2][3];

        if (course.equals(res.getString(R.string.course1))) {
            Coursewaiting = ((varStore) getApplication()).gethub1020sWaiting();
            courseMarks = ((varStore) getApplication()).gethub1020sMarks();
            listItemHeadings = res.getStringArray(R.array.listItemTilesHeading);
        } else if (course.equals(res.getString(R.string.course2))) {
            Coursewaiting = ((varStore) getApplication()).getibs1007sWaiting();
            courseMarks = ((varStore) getApplication()).ibs1007sMarks();
            listItemHeadings = res.getStringArray(R.array.listItemTilesHeading2);
        } else if (course.equals(res.getString(R.string.course3))) {
            Coursewaiting = ((varStore) getApplication()).gethub1019fWaiting();
            courseMarks = ((varStore) getApplication()).gethub1019fMarks();
            listItemHeadings = res.getStringArray(R.array.listItemTilesHeading);
        } else if (course.equals(res.getString(R.string.course4))) {
            Coursewaiting = ((varStore) getApplication()).gethub1006fWaiting();
            courseMarks = ((varStore) getApplication()).getHUB1006fMarks();
            listItemHeadings = res.getStringArray(R.array.listItemTilesHeading2);
        } else {
            Toast.makeText(this, "Problem with course weighting, useing HUB1020S weighting", Toast.LENGTH_LONG).show();

            Coursewaiting = ((varStore) getApplication()).gethub1020sWaiting();
            if (((varStore) getApplication()).gethub1020sMarks() != null) {
                courseMarks = ((varStore) getApplication()).gethub1020sMarks();
            } else {
                courseMarks = null;
            }

        }
/*
   <string name="course1">HUB1020S</string>
    <string name="course2">IBS1007S</string>
    <string name="course3">HUB1020f</string>
    <string name="course4">IBS1007f</string>

*/

        //set up display
        mainMenuetxt1.setText(course);
        //set up lists
        MainListView = (ListView) findViewById(R.id.MainListView);


        percantagesIntArray = new int[listItemHeadings.length][3];

        int testdone, testnotdone, assingments, assingmentsnotdone;
        //todo put grades in from var store
        if (courseMarks == null) {
            Toast.makeText(this, "Useing dummy marks.", Toast.LENGTH_LONG).show();
            testdone = 30; //courseMarks[0][0];
            testnotdone = 50;//courseMarks[1][0];
            assingments = 70;//courseMarks[0][1];
            assingmentsnotdone = 10;//courseMarks[1][1];
        } else {
            // Toast.makeText(this," "+ ((varStore)getApplication()).getgradebookText(), Toast.LENGTH_LONG).show();
            testdone = courseMarks[0][0];
            testnotdone = courseMarks[1][0];
            assingments = courseMarks[0][1];
            assingmentsnotdone = courseMarks[1][1];

        }


        //test/
        percantagesIntArray[1][0] = testdone;//got
        percantagesIntArray[1][1] = testnotdone;//future
        percantagesIntArray[1][2] = (100 - testnotdone) - testdone;//lost
        //2 assINMENTS
        percantagesIntArray[2][0] = assingments;
        percantagesIntArray[2][1] = assingmentsnotdone;
        percantagesIntArray[2][2] = (100 - assingmentsnotdone) - assingments;

        //0 avg
        percantagesIntArray[0][0] = (int) (testdone * Coursewaiting[1]) + (int) (assingments * (Coursewaiting[2] + Coursewaiting[3]));//got

        percantagesIntArray[0][1] = (int) (testnotdone * Coursewaiting[1]) + (int) (assingmentsnotdone * (Coursewaiting[2] + Coursewaiting[3]));//future

        percantagesIntArray[0][2] = (100 - percantagesIntArray[0][0]) - percantagesIntArray[0][1];//lost

          /* //old lazy fill
            for (int i=0; i<listItemHeadings.length;i++){
                percantagesIntArray[i][0]= 50;
                percantagesIntArray[i][1]=25;
                percantagesIntArray[i][2]=25;

            }
      */
        ListItemAdapter listItemAdapter = new ListItemAdapter(this, listItemHeadings, percantagesIntArray);
        MainListView.setAdapter(listItemAdapter);
        //   Toast.makeText(this,((varStore)getApplication()).getgradebookText(), Toast.LENGTH_LONG).show();

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
