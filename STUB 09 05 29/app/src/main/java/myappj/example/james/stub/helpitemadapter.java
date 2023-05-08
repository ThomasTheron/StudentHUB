package myappj.example.james.stub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class helpitemadapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] hHeadings;
    String[] hbodys;
    String[] hLinks;
    Context context;
    String studentNumber;
    String AchivmentsFileC;

    public helpitemadapter(Context c, String[] hHeadingsin, String[] hbodysin, String[] hLinksin, String studentNumberIN, String AchivmentsFileIN) {
        hHeadings = hHeadingsin;
        hbodys = hbodysin;
        hLinks = hLinksin;
        context = c;
        studentNumber = studentNumberIN;
        AchivmentsFileC = AchivmentsFileIN;

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return hHeadings.length;
    }

    @Override
    public Object getItem(int position) {
        return hHeadings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = mInflater.inflate(R.layout.listviewdetail_helppage, null);
        TextView head = (TextView) v.findViewById(R.id.head);
        TextView body = (TextView) v.findViewById(R.id.body);
        Button linkbtn = (Button) v.findViewById(R.id.link);

        head.setText(hHeadings[position]);
        body.setText(hbodys[position]);
        if (hLinks[position].equals("https://www.google.com/gmail/")) {
            linkbtn.setText("Open gmail link");

        } else {
            linkbtn.setText("Open " + hHeadings[position] + " link");
        }


        final String adress = hLinks[position];

        //Button gotoHelpbtn = (Button) findViewById(R.id.helpbtn);
        linkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriadress = Uri.parse(adress);
                Intent openBrowser = new Intent(Intent.ACTION_VIEW, uriadress);
                if (openBrowser.resolveActivity(context.getPackageManager()) != null) {

                    Object[] userNameNFile = new Object[2];
                    userNameNFile[0] = studentNumber;
                    String AchivmentsFile = AchivmentsFileC;
                    String[] AchivmentsSplit = AchivmentsFile.split(",");
                    int[] AValues = new int[AchivmentsSplit.length];
                    for (int i = 1; i < AchivmentsSplit.length; i++) {
                        AValues[i] = Integer.parseInt(AchivmentsSplit[i]);
                    }
                    AValues[2] = AValues[2] + 1;

                    userNameNFile[1] = "start,true," + AValues[1] + "," + AValues[2] + "," + AValues[3] + "," + AValues[4] + ",end";//todo update when more values come in
                    achivmentsUpdaterNetwork task2 = new achivmentsUpdaterNetwork();
                    Object ans2 = null;

                    try {
                        //updates achivmentsActivity file stored on backend
                        ans2 = task2.execute(userNameNFile).get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    context.startActivity(openBrowser);
                } else {
                    System.out.println("problem");
                }
            }
        });


        return v;
    }
}
