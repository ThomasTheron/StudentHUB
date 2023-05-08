package myappj.example.james.stub;

import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//senmds login request with username and hashed password
//backend replys with a page that gets checked in   loginActivity.java
public class authenticatorNetwork extends AsyncTask {


    @Override
    protected Object doInBackground(Object[] objects) {
        String username = (String) objects[0];
        String pass = (String) objects[1];
        String hashedPass = hash(pass);

        String aurla = "https://stuhub.cs.uct.ac.za/authenticate.php?id=" + username + "&password=" + hashedPass;

        String thetext = "didntwork";
        try {
            URL url = new URL(aurla);
            thetext = new Scanner(url.openStream()).useDelimiter("\\A").next();
            //Toast.makeText(this.,"text", Toast.LENGTH_LONG).show();
        } catch (MalformedURLException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
            // Toast.makeText(this,"a", Toast.LENGTH_LONG).show();
        } catch (IOException ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
            // Toast.makeText(this,"b", Toast.LENGTH_LONG).show();
        }


        return thetext;
    }

    protected String hash(String pass) {


        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pass.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            System.out.println("hashtext" + hashtext);
            return hashtext;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }


    }
}
