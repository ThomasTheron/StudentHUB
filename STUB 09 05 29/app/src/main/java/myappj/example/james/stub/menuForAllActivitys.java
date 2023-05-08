package myappj.example.james.stub;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class menuForAllActivitys {


    Activity CurrentActivity;

    public menuForAllActivitys(Activity a, MenuItem INitem) {
        CurrentActivity = a;

    }


    public boolean JonOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //item 1 has 2 subitems (hardcoded) in menue_screan_selcetion.xml
        if (id == R.id.subItem1) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), DashBordActivity.class);
            startIntent.putExtra("courseCode", CurrentActivity.getResources().getString(R.string.course1));
            CurrentActivity.startActivity(startIntent);
        } else if (id == R.id.subItem2) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), DashBordActivity.class);
            startIntent.putExtra("courseCode", CurrentActivity.getResources().getString(R.string.course2));
            CurrentActivity.startActivity(startIntent);

        } else if (id == R.id.subItem3) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), DashBordActivity.class);
            startIntent.putExtra("courseCode", CurrentActivity.getResources().getString(R.string.course3));
            CurrentActivity.startActivity(startIntent);
        } else if (id == R.id.subItem4) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), DashBordActivity.class);
            startIntent.putExtra("courseCode", CurrentActivity.getResources().getString(R.string.course4));
            CurrentActivity.startActivity(startIntent);
        } else if (id == R.id.item2) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), helpPageActivity.class);
            CurrentActivity.startActivity(startIntent);
            return true;
        } else if (id == R.id.item3) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), ConsentInfoPageActivity.class);
            CurrentActivity.startActivity(startIntent);
            return true;
        } else if (id == R.id.item4) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), achivmentsActivity.class);
            CurrentActivity.startActivity(startIntent);

            return true;
        } else if (id == R.id.item5) {
            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), tutorialPageActivity.class);
            CurrentActivity.startActivity(startIntent);
            return true;
        } else if (id == R.id.item6) {//password change item

            Toast.makeText(CurrentActivity, "plz wait", Toast.LENGTH_LONG).show();

            Object[] studentNumberObjectarray = new Object[1];
            studentNumberObjectarray[0] = ((varStore) CurrentActivity.getApplication()).getStudentNumber();

            passwordChangerNetwork passwordChanger = new passwordChangerNetwork();

            Object ans = null;
            try {
                ans = passwordChanger.execute(studentNumberObjectarray).get();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            if (ans != null && ans != "didntwork" && !(((String) ans).contains("Your password recovery key has been sent"))) {

                Toast.makeText(CurrentActivity, R.string.passwordchangeNo, Toast.LENGTH_LONG).show();

            } else {
                //  update achivment
                Object[] userNameNFile = new Object[2];
                userNameNFile[0] = ((varStore) CurrentActivity.getApplication()).getStudentNumber();
                String AchivmentsFile = ((varStore) CurrentActivity.getApplication()).getAchivmentsText();
                String[] AchivmentsSplit = AchivmentsFile.split(",");
                int[] AValues = new int[AchivmentsSplit.length];

                for (int i = 1; i < AchivmentsSplit.length; i++) {
                    AValues[i] = Integer.parseInt(AchivmentsSplit[i]);

                }
                //update NO logins
                AValues[4] = 15;
                userNameNFile[1] = "start,true," + AValues[1] + "," + AValues[2] + "," + AValues[3] + "," + AValues[4] + ",end";
                achivmentsUpdaterNetwork task2 = new achivmentsUpdaterNetwork();
                Object ans2 = null;
                try {
                    ans2 = task2.execute(userNameNFile).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }


                Toast.makeText(CurrentActivity, R.string.passwordchangeYes, Toast.LENGTH_LONG).show();
            }


            return true;
        } else if (id == R.id.item7) {


            Intent startIntent = new Intent(CurrentActivity.getApplicationContext(), LoginActivity.class);
            CurrentActivity.startActivity(startIntent);
            return true;
        }

        return false;
    }


}
