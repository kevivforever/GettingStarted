package in.finder.gettingstarted.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBoundService extends Service {

    private final IBinder binder = new MyLocalBinder();

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public String getCurrentTime(){
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss", Locale.US);
        return (date.format(new Date()));
    }

    public class MyLocalBinder extends Binder {
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }
}
