package in.finder.gettingstarted.Services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import in.finder.gettingstarted.R;

public class BoundServiceActivity extends AppCompatActivity {

    MyBoundService myBoundService;
    boolean isBound =  false;
    TextView mTextViewCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_service);

        mTextViewCurrentTime = (TextView) findViewById(R.id.textView4);
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, boundServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection boundServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.MyLocalBinder myLocalBinder = (MyBoundService.MyLocalBinder) service;
            myBoundService = myLocalBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void showTime(View view) {
        String currentTime = myBoundService.getCurrentTime();
        mTextViewCurrentTime.setText(currentTime);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(boundServiceConnection);
        }
    }
}
