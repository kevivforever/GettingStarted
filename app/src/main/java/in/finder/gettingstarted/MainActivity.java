package in.finder.gettingstarted;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import in.finder.gettingstarted.Maps.MapActivity;
import in.finder.gettingstarted.RecyclerView.CardViewDemoActivity;
import in.finder.gettingstarted.Services.BoundServiceActivity;
import in.finder.gettingstarted.designlibrary.NavigationActivity;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.finder.gettingstarted.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View .OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    public  void startNavigationActivity(View view){
        Intent intent = new Intent(this, NavigationActivity.class);
        startActivity(intent);
    }

    public  void startCardViewDemoActivity(View view){
        Intent intent = new Intent(this, CardViewDemoActivity.class);
        startActivity(intent);
    }

    public void startGoogleMapActivity(View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    public void startBoundServiceActivity(View view){
        startActivity(new Intent(this, BoundServiceActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
