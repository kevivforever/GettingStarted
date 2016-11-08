package in.finder.gettingstarted.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("gettingstarted", "service on create");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("gettingstarted", "service started");
        Thread thread = new Thread(new mythread(startId));
        thread.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v("gettingstarted", "service destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    final class mythread implements Runnable{
        int serviceId;
        mythread(int serviceId){
            this.serviceId = serviceId;
        }

        @Override
        public void run() {
            synchronized (this){
                try {
                    wait(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stopSelf(this.serviceId);
            }
        }
    }
}
