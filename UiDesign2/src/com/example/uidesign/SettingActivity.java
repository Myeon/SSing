package com.example.uidesign;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SettingActivity extends Activity implements SensorEventListener{
	SharedPreferences pref_fonts;
	String fonts;
	TextView tv1;
	SeekBar seek;
	int pre_progress;
	Button btn1, btn3;
	SharedPreferences pref_shake;
	ImageView img;
	private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
   
    private float x, y, z;
    private int SHAKE_THRESHOLD;
   
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
   
    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;
    SharedPreferences pref;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        pref = getSharedPreferences("option", 0);
        
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        pref_fonts = getSharedPreferences("customFonts", 0);
   		fonts = pref_fonts.getString("customFonts", "fonts/DroidSans.ttf");
		
   		Typeface face=Typeface.createFromAsset(getAssets(), fonts);
   		
   		tv1 = (TextView)findViewById(R.id.text_setting);
   		
        seek = (SeekBar)findViewById(R.id.seekBar1);
        btn1 = (Button)findViewById(R.id.btn_ok);
        img = (ImageView)findViewById(R.id.image1);
        
        btn3 = (Button)findViewById(R.id.btn_re);
   		tv1.setTypeface(face);
        
   		seek.setOnSeekBarChangeListener(listenGenerator);
        
   		pref_shake = getSharedPreferences("customShake", 0);
   		SHAKE_THRESHOLD = pref_shake.getInt("customShake", 3000);
        
        seek.setProgress(SHAKE_THRESHOLD);
        
        
        
   	    btn1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor edit = pref_shake.edit();
				edit.putInt("customShake", SHAKE_THRESHOLD);
				edit.commit();
				
				if(pref.getBoolean("serviceFlag", true) == true)
					 startService(new Intent("com.example.uidesign"));
				finish();
			}
		});
   	    
  	    
   	    btn3.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
        		
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		pref = getSharedPreferences("option", 0);
		if(pref.getBoolean("serviceFlag", true) == true)
			 stopService(new Intent("com.example.uidesign"));
	}

	@Override
    public void onStart() {
        super.onStart();
   
        if (accelerormeterSensor != null)
            sensorManager.registerListener(SettingActivity.this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }
   
    @Override
    public void onStop() {
        super.onStop();
   
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
   
                if (speed > SHAKE_THRESHOLD) {
                    // 이벤트 발생!!
                	Toast.makeText(SettingActivity
                			.this, "감지하였습니다.", Toast.LENGTH_LONG).show();
                	onStop();
                	img.setImageResource(R.drawable.blue);
                }
                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    } 

    private SeekBar.OnSeekBarChangeListener listenGenerator = new SeekBar.OnSeekBarChangeListener() {
		//thumb 놓았을 때 날라오는 메세지
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
//			Toast.makeText(SettingActivity.this, pre_progress, Toast.LENGTH_LONG).show();
			//위치가 변하면 색깔 빨간색으로 다시 바꿔야함
			img.setImageResource(R.drawable.red);
			onStart();
		}
		//thumb 잡았을 떄 날라오는 메세지
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		//thumb 위치가 변경될 때마다 오는 메세지
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			SHAKE_THRESHOLD = progress;
//			tvPro.setText(new StringBuilder().append(progress));
//			Toast.makeText(SettingActivity.this, progress, Toast.LENGTH_LONG).show();
			
			
			
		}
	};
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }

    
}
