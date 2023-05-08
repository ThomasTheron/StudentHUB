package myappj.example.james.stub;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class achivmentsUpdaterNetwork extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {

        String username = (String) objects[0];

        String newAchFile = (String) objects[1];
        String aurla = "https://stuhub.cs.uct.ac.za/ach_set.php?id=" + username + "&data=" + newAchFile;


        String thetext = "didntwork";

        //should just work. stream not needed but needs to open page so email can be sent
        try {
            URL url = new URL(aurla);
            thetext = new Scanner(url.openStream()).useDelimiter("\\A").next();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
        }


        return thetext;
    }
}
