package com.example.uidesign;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class ShakeTest extends Service implements SensorEventListener{

	public IBinder onBind(Intent arg0){
		return null;
	}
	private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
   
    private float x, y, z;
    private int SHAKE_THRESHOLD;
    SharedPreferences pref_shake;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
   
    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;
    private boolean flag;
   
    public void onCreate() {
        super.onCreate();
        
        pref_shake = getSharedPreferences("customShake", 0);
        SHAKE_THRESHOLD = pref_shake.getInt("customShake", 3000);
   		
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
   
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
		super.onStartCommand(intent, flags, startId);
		flag = true;
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
        return START_REDELIVER_INTENT;
    }
    
    
    public void onDestroy(){
		super.onDestroy();
   
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }
   
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
   
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
   
            if (gabOfTime > 100) {
                lastTime = currentTime;
   
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];
   
                speed = Math.abs(x + y + z - lastX - lastY - lastZ) /
                        gabOfTime * 10000;
   
                if (speed > SHAKE_THRESHOLD && flag == true) {
                    // 이벤트 발생!!
                	Context context = getApplicationContext();
                	Intent intent = new Intent(context, SendActivity.class);
                	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
                	context.startActivity(intent);
                	flag = false;
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    } 
}
