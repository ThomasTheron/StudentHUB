package myappj.example.james.stub;

import android.app.Activity;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//fetches achivmentsActivity file
//fetches course marks
//used to fetch tut info

public class fileFetcherNetwork extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        String aurla1 = "https://stuhub.cs.uct.ac.za/main_tut.php";
        String aurla2 = "https://stuhub.cs.uct.ac.za/ach_get.php?id=" + objects[0];//objects[0]= user name


        String aurla3 = "https://stuhub.cs.uct.ac.za/pvt/get_marks.php?id=" + (String) objects[0];


        String achivments = "didntwork";
        String tutorialMain = "didntwork";
        String grades = "didntwork";

        Object[] filesToReturn = new Object[4];

        try {
            URL url2 = new URL(aurla2);
            achivments = new Scanner(url2.openStream()).useDelimiter("\\A").next();

            if (achivments.indexOf("start") != -1) {    //if file exists
                String achivmentsOutput2 = (String) achivments.subSequence(achivments.indexOf("start") + 6, achivments.length());//text.indexOf("start"), text.indexOf("end")
                achivmentsOutput2 = (String) achivmentsOutput2.subSequence(0, achivmentsOutput2.indexOf("end") - 1);
                achivments = achivmentsOutput2;

            } else {
                achivments = "false";//what to return if no achivmentsActivity file found

            }


            //block for getiing grade book
            URL url3;
            try {
                url3 = new URL(aurla3);
                grades = new Scanner(url3.openStream()).useDelimiter("\\A").next();


            } catch (MalformedURLException e) {
                System.out.println("MalformedURLException url wasnt correct");

            }


            //dear future hons students. The word end may be found at the incorect index if there are words in the php script that include the word end
            try {
                if (grades.indexOf("start") != -1 && grades.indexOf("endend") != -1) {
                    String gradesoutput = (String) grades.subSequence(grades.indexOf("start") + 6, grades.length());//text.indexOf("start"), text.indexOf("end")
                    grades = (String) gradesoutput.subSequence(0, gradesoutput.indexOf("endend"));
                } else {
                    grades = "failedtogetgrades+" + grades.indexOf("start") + " " + grades.indexOf("endend");
                    //problem grades didnt come through///TODO what to do if grades dont come through
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("problem with file fetcher");
            }

            //block for filling array that gets returend//todo swap set string to actual string
            filesToReturn[0] = "no file";//tutorialMainOutput;
            filesToReturn[1] = grades;
            filesToReturn[2] = achivments;
            filesToReturn[3] = new String[3];

        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
        }


        return filesToReturn;
    }
}
