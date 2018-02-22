package in.finder.gettingstarted.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import in.finder.gettingstarted.Adapters.SlideAdapter;
import in.finder.gettingstarted.R;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private SlideAdapter mSlideAdapter;
    private LinearLayout mLinearLayout;
    private Button mButton;
    private TextView[] mDotTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        mViewPager = findViewById(R.id.onboarding_viewpager);
        mSlideAdapter = new SlideAdapter(this);
        mViewPager.setAdapter(mSlideAdapter);
        mViewPager.addOnPageChangeListener(viewListener);

        mLinearLayout = findViewById(R.id.onboarding_dot_layout);

        mButton = findViewById(R.id.onboarding_bt_mainactivity);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingActivity.this, MovieActivity.class));
            }
        });

        addDotIndicator(0);
    }

    public void addDotIndicator(int position){
        mDotTextView = new TextView[3];
        mLinearLayout.removeAllViews();

        for(int i =0; i<mDotTextView.length; i++){
            mDotTextView[i] = new TextView(this);
            mDotTextView[i].setText(Html.fromHtml("&#8226;"));
            mDotTextView[i].setTextSize(35);
            mDotTextView[i].setTextColor(getResources().getColor(R.color.colorGreyLight));
            mLinearLayout.addView(mDotTextView[i]);
        }

        if (mDotTextView.length > 0){
            mDotTextView[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
