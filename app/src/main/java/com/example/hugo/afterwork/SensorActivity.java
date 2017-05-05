package com.example.hugo.afterwork;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorActivity implements SensorEventListener {

    private final SensorManager mSensorManager;
    private final Sensor mAccelerometer;
    private final MainActivity mainActivity;

    public SensorActivity(SensorManager sm, MainActivity ma){
        mSensorManager = sm;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.mainActivity = ma;
    }

    protected void onResume() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Ne sert à rien dans ce cas là, mais fait partie de l'interface SensorEventListener
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.values[0]>10){
            mainActivity.onWiggle();
        }
    }
}