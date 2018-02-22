package in.finder.gettingstarted.Views;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import in.finder.gettingstarted.R;

/**
 * Created by keviv on 24/01/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public DrawerLayout mDrawerlayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    public ActionBarDrawerToggle mActionBarDrawerToggle;

    @Override
    public void setContentView(int layoutResID) {
        mDrawerlayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = mDrawerlayout.findViewById(R.id.base_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(mDrawerlayout);

        mNavigationView = findViewById(R.id.base_navigationview);
        mNavigationView.setNavigationItemSelectedListener(this);

        View navigationHeaderView = mNavigationView.getHeaderView(0);
        final TextView mTextViewUserName = navigationHeaderView.findViewById(R.id.nav_header_tv_username);

        mToolbar = findViewById(R.id.base_toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        if (useToolbar()) {
            setSupportActionBar(mToolbar);
        } else {
            mToolbar.setVisibility(View.GONE);
        }
        setUpNavView();
    }

    protected void setUpNavView() {

        if (useDrawerToggle()) {
            mActionBarDrawerToggle = new ActionBarDrawerToggle(
                    this, mDrawerlayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerlayout.addDrawerListener(mActionBarDrawerToggle);
        } else if (useToolbar() && getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    protected abstract boolean useDrawerToggle();

    protected abstract boolean useToolbar();

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movie_activity) {

        } else if (id == R.id.nav_github_activity) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.base_drawer_layout);
        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(useDrawerToggle()) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(useDrawerToggle()) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }
}
