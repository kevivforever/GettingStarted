package in.finder.gettingstarted.Services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import in.finder.gettingstarted.R;

public class MainServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
    }

    public void startService(View view) {
        startService(new Intent(this,MyService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(this,MyService.class));
    }

    public void startIntentService(View view) {
        MyIntentService.startActionFoo(this, "vivek", "naik");
    }

    public void stopIntentService(View view) {
        stopService(new Intent(this, MyIntentService.class));
    }
}
