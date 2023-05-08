package myappj.example.james.stub;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class ListItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] listItemTiles;

    int[][] percantagesIntArray;
    Context context;
    //first index = postion
    //second dimention got lost future
    //percantagesIntArray[position][3] is the part to put in loading bar

    public ListItemAdapter(Context c, String[] namesOfItems, int[][] marks) {
        context = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        listItemTiles = namesOfItems;

        percantagesIntArray = marks;
    }

    @Override
    public int getCount() {
        return listItemTiles.length;
    }

    @Override
    public Object getItem(int position) {
        return listItemTiles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //get all components
        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView HeadingTextView = (TextView) v.findViewById(R.id.headingtxt);
        TextView GotmarkTextView = (TextView) v.findViewById(R.id.gotMarktxt);
        TextView LostMarkTextView = (TextView) v.findViewById(R.id.lostMarktxt);
        TextView FutureMarkTextView = (TextView) v.findViewById(R.id.futureMarkstxt);

        String heading = listItemTiles[position];
        String gotString = "Marks acquired \n" + percantagesIntArray[position][0] + "%";
        String futureString = "Marks still acquirable\n" + percantagesIntArray[position][1] + "%";
        String lostString = "Marks no longer acquirable\n" + percantagesIntArray[position][2] + "%";
        int got = percantagesIntArray[position][0];

        HeadingTextView.setText(heading);
        GotmarkTextView.setText(gotString);
        LostMarkTextView.setText(lostString);
        FutureMarkTextView.setText(futureString);


        //find images to make loading bar from
        ImageView green = v.findViewById(R.id.green);
        ImageView yelow = v.findViewById(R.id.yellow);
        ImageView red = v.findViewById(R.id.red);
        //calulate widths
        int screanWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        int greenwidth = (percantagesIntArray[position][0]) * screanWidth / 100;
        int yelowwidth = (percantagesIntArray[position][1]) * screanWidth / 100;
        int redwidth = (percantagesIntArray[position][2]) * screanWidth / 100;
        //set widths
        green.getLayoutParams().width = greenwidth;
        yelow.getLayoutParams().width = yelowwidth;
        red.getLayoutParams().width = redwidth;
        //set positions
        green.setX(0);
        yelow.setX(greenwidth);
        red.setX(greenwidth + yelowwidth);


        return v;
    }
}
