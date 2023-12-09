package com.wl.odometer;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.TextView;

import java.util.Random;

public class OdometerService extends Service {

    private final Random random = new Random();
    public class OdometerBinder extends Binder {
        OdometerService getOdometer(){
            return OdometerService.this;
        }
    }
    private final IBinder binder = new OdometerBinder();


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public double getDistance(){
        return random.nextDouble();
    }


}