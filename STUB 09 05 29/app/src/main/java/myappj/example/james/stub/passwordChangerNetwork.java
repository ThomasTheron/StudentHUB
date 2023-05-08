package myappj.example.james.stub;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class passwordChangerNetwork extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {


        String aurla = "https://stuhub.cs.uct.ac.za/change.php?mymail=" + objects[0] + "@myuct.ac.za   ";


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
