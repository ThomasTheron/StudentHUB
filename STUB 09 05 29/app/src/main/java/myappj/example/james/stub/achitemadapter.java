package myappj.example.james.stub;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class achitemadapter extends BaseAdapter {

    LayoutInflater mInflater;
    String[] AHeadings;
    String[] Abodys;
    int[] AstarValues;


    Context context;

    public achitemadapter(Context c, String[] AHeadingsin, String[] Abodysin, int[] values) {
        AHeadings = AHeadingsin;
        Abodys = Abodysin;
        AstarValues = values;
        context = c;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return AHeadings.length;
    }

    @Override
    public Object getItem(int position) {
        return AHeadings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.listviewdetail_achivment, null);

        TextView ahead = (TextView) v.findViewById(R.id.aHead);
        TextView abody = (TextView) v.findViewById(R.id.aBody);
        TextView achivmentCountText = (TextView) v.findViewById(R.id.achivmentcount);
        ImageView badge = (ImageView) v.findViewById(R.id.StarImageView);


        Resources res = context.getResources();
        Drawable star;
        if (AstarValues[position] / 5 == 0) {
            star = res.getDrawable(R.drawable.s0);
            achivmentCountText.setText(AstarValues[position] + " " + res.getString(R.string.achivmentMotivator) + " 5 " + res.getString(R.string.points));

        } else if (AstarValues[position] / 5 == 1) {
            star = res.getDrawable(R.drawable.s1);
            achivmentCountText.setText(AstarValues[position] + " " + res.getString(R.string.achivmentMotivator) + " 10 " + res.getString(R.string.points));
        } else if (AstarValues[position] / 5 == 2) {
            star = res.getDrawable(R.drawable.s2);
            achivmentCountText.setText(AstarValues[position] + " " + res.getString(R.string.achivmentMotivator) + " 15 " + res.getString(R.string.points));
        } else {
            star = res.getDrawable(R.drawable.s2);
            achivmentCountText.setText(AstarValues[position] + " " + res.getString(R.string.achivmentCompleated));
        }

        if (badge != null) {
            badge.setImageDrawable(star);
        }


        // badge.setImageDrawable(star);

        ahead.setText(AHeadings[position]);
        abody.setText(Abodys[position]);


        return v;
    }
}
