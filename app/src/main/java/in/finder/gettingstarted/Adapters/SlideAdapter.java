package in.finder.gettingstarted.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import in.finder.gettingstarted.R;

/**
 * Created by keviv on 12/02/2018.
 */

public class SlideAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    public int[] backgroundColors = {
            R.color.colorPrimary,
            R.color.orange,
            R.color.colorAccentDark
    };

    public int[] images = {
            R.drawable.group_10,
            R.drawable.group_11,
            R.drawable.group_12
    };

    public String[] titles = {
            "Group 10", "Group 11", "Group 12"
    };

    public String[] descriptionList = {
            "Group 10 description", "Group 11 description", "Group 12 description"
    };

    public SlideAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = mLayoutInflater.inflate(R.layout.slide, container, false);

        ImageView imageView = view.findViewById(R.id.slide_imageview);
        TextView primaryTextView = view.findViewById(R.id.slide_tv_title);
        TextView secondaryTextView = view.findViewById(R.id.slide_tv_description);

        imageView.setImageResource(images[position]);
        primaryTextView.setText(titles[position]);
        secondaryTextView.setText(descriptionList[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
