package com.example.uidesign;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.SurfaceHolder.Callback;
import android.view.*;
import android.widget.*;

public class SendActivity extends Activity implements Callback, View.OnClickListener {
	
	MediaPlayer mp ;
	SharedPreferences pref;
	SharedPreferences pref_sound;
	boolean play;
	Thread thread;
	String sql;
	LocationManager locationManager;
	String locationProvider;
 	String lat, lon;
 	String Time;
 	String message;
 	String phone_num;
 	String url;
 	ArrayList list = new ArrayList();
 	private SurfaceHolder surfaceHolder;
	private SurfaceView surfaceView;
	public MediaRecorder mrec = new MediaRecorder();
	Location location;
	File video;
	private Camera mCamera;
	boolean recording;
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		 pref = getSharedPreferences("option", 0);
		 if(pref.getBoolean("serviceFlag", true) == true)
			 startService(new Intent("com.example.uidesign"));
		locationManager.removeUpdates(listener);
		 play = false;
		 pref_sound = getSharedPreferences("selectsound", 0);
		 
		 if(mp != null)
		 {
			mp.stop();
			mp.release();
			thread.interrupt();
		 }
		 //동영상 촬영이 끝난 후 미디어 스캐닝을 해줘야 갤러리 에서 파일을 재생시킬 수 있다.
		 sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ "DCIM/Camera"+"video"+".mp4")));

	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(pref.getBoolean("serviceFlag", true) == true)
			 stopService(new Intent("com.example.uidesign"));
		
		locationManager.requestLocationUpdates(locationProvider, 5000, 10, listener);//5초, 10미터 마다
		play = true;
		   
		switch(pref_sound.getInt("selectsound",0))
		{
			case 0:
				mp = null;
				break;
			case 1 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.emer_alarm);
				  break;
			case 2 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.emer_alarm2);
				  break;
			case 3 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.mopp);
				  break;
			case 4 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.scream);
				  break;
			case 5 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.scream2);
				  break;
			case 6 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.siren);
				  break;
			case 7 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.siren2);
				  break;
			case 8 :
				  mp = MediaPlayer.create(SendActivity.this, R.raw.smoke_alarm);
				  break;
			}
			 
	      try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(mp != null){
	      thread = new Thread(){

	  	  @Override
	  		public void run() {
	  			// TODO Auto-generated method stub
	  			super.run();
	  			mp.setLooping(true);
	  			mp.start();
	  		} 
	  	  };
	  	      thread.start();
	    }
	}
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_send);


getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

	    
	    pref_sound = getSharedPreferences("selectsound", 0);
	    pref = getSharedPreferences("option", 0);
//	    startService(new Intent("com.sms"));
	    
	    if (pref.getBoolean("cameraFlag", true) == true)
	    {
	    Log.i(null , "Video starting");

	    surfaceView = (SurfaceView) findViewById(R.id.SurfaceView);
	    surfaceHolder = surfaceView.getHolder();

	    surfaceHolder.addCallback(this);

	    surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

	    mCamera = Camera.open();
	    mCamera.setDisplayOrientation(90); 
	    
	    //Toast.makeText(this, "녹화시작/끝, 화면터치", Toast.LENGTH_SHORT).show();
	    Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
          public void run()
          {
        	  try {
      			initRecording();
      			startRecording();
      		} catch (Exception e) {
      			String message = e.getMessage();
      			Log.i(null, "Problem Start"+message);
      			mrec.release();
      		}
          }
        }, 2500);
	    }
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date(System.currentTimeMillis());
    	
		Time = dateFormat.format(date);
	
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
	    	
		//위치정보 공급자 얻어오기
    	locationProvider = locationManager.getBestProvider(new Criteria(), true);
    	//Toast.makeText(this, "위치정보 공급자:"+locationProvider, 0).show();
    	locationManager.requestLocationUpdates(locationProvider, 10000, 100, listener);
    	//가장 최근의 Location 객체 얻어오기
    	location = locationManager.getLastKnownLocation(locationProvider);
    	if(location == null){
    		try{
    			Thread.sleep(10000);
    		}catch(InterruptedException e){
    			e.printStackTrace();
    		}
    		location = locationManager.getLastKnownLocation(locationProvider);
    	}
    	if(location != null){
    		lat = location.getLatitude()+"";
			lon = location.getLongitude()+"";
			SQLiteOpenHelper dbHelper = new DBManager(SendActivity.this, "reg.db");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			sql = "insert into mLog values(null, '"+Time+"', '"+lat+"', '"+lon+"');";
    		db.execSQL(sql);
    	}
    	SQLiteOpenHelper dbHelper = new DBManager(SendActivity.this, "reg.db");
    	SQLiteDatabase db = dbHelper.getWritableDatabase();
	
		sql = "select *from members;";
		String[] columns = {"phone_num"};
		Cursor result = db.query("members", columns, null, null, null, null, null);
		while(result.moveToNext()){
			String number = result.getString(0);
			list.add(number);
		}
		
		SharedPreferences pref_msg = getSharedPreferences("messageText", MODE_PRIVATE);
		SharedPreferences.Editor edit = pref_msg.edit();
		url = "http://maps.google.com/maps?q="+lat+","+lon+"+&iwloc=A&hl=kr";
		message = pref_msg.getString("messageText", " ")+"\n발생시간: "+Time+" 위치확인: " + url;
		System.out.println(message);
		for(int i=0; i<list.size(); i++){
			phone_num = list.get(i).toString();
			sendSMS(phone_num, message);                
		}
		 Toast.makeText(getBaseContext(), "SMS sent", 
                 Toast.LENGTH_SHORT).show();
	
	}

	private void sendSMS(String phoneNumber, String message){
			String SENT = "SMS_SENT";
	        String DELIVERED = "SMS_DELIVERED";

	       PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	           new Intent(SENT), 0);

	       PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	           new Intent(DELIVERED), 0);

	       //---when the SMS has been sent---
	       registerReceiver(new BroadcastReceiver(){
	           @Override
	           public void onReceive(Context arg0, Intent arg1) {
	               switch (getResultCode())
	               {
	                   case Activity.RESULT_OK:
	                       Toast.makeText(getBaseContext(), "SMS sent", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	                   case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
	                       Toast.makeText(getBaseContext(), "Generic failure", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	                   case SmsManager.RESULT_ERROR_NO_SERVICE:
	                       Toast.makeText(getBaseContext(), "No service", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	                   case SmsManager.RESULT_ERROR_NULL_PDU:
	                       Toast.makeText(getBaseContext(), "Null PDU", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	                   case SmsManager.RESULT_ERROR_RADIO_OFF:
	                       Toast.makeText(getBaseContext(), "Radio off", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	               }
	           }
	       }, new IntentFilter(SENT));

	       //---when the SMS has been delivered---
	       registerReceiver(new BroadcastReceiver(){
	           @Override
	           public void onReceive(Context arg0, Intent arg1) {
	               switch (getResultCode())
	               {
	                   case Activity.RESULT_OK:
	                       Toast.makeText(getBaseContext(), "SMS delivered", 
	                               Toast.LENGTH_SHORT).show();
	                       break;
	                   case Activity.RESULT_CANCELED:
	                       Toast.makeText(getBaseContext(), "SMS not delivered", 
	                               Toast.LENGTH_SHORT).show();
	                       break;                        
	               }
	           }
	       }, new IntentFilter(DELIVERED)); 
	       
	       SmsManager sms = SmsManager.getDefault(); 
	       sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);		
	 }
	
	LocationListener listener = new LocationListener() {
    	//상태가 바뀌었을 때
    	public void onStatusChanged(String provider, int status, Bundle extras) {
    		String msg = "";
    		switch(status){
    			case LocationProvider.OUT_OF_SERVICE :
    				msg = "서비스 지역이 아닙니다.";
    				break;
    			case LocationProvider.TEMPORARILY_UNAVAILABLE :
    				msg = "일시적으로 위치 정보를 사용할 수 없습니다.";
    				break;
    			case LocationProvider.AVAILABLE :
    				msg = "서비스 가능 지역입니다.";
    				break;
    		}
    	
    	}
    	//공급자가 공급 가능하게 되었을 때
    	public void onProviderEnabled(String provider) {
   
    	}
    	//공급자가 공급 못하게 되었을 때
    	public void onProviderDisabled(String provider) {
   
    	}
    	//위치 정보가 바뀌엇을때 호출되는 메소드
    	public void onLocationChanged(Location location) {
    		locationProvider = locationManager.getBestProvider(new Criteria(), true);
        	//Toast.makeText(this, "위치정보 공급자:"+locationProvider, 0).show();
        	locationManager.requestLocationUpdates(locationProvider, 10000, 100, listener);
        	//가장 최근의 Location 객체 얻어오기
        	location = locationManager.getLastKnownLocation(locationProvider);
    	}
    }; 
    
	private void initRecording() throws IllegalStateException, IOException {
	    if(mrec==null)
	    	mrec = new MediaRecorder();  // Works well
	    mCamera.unlock();
	
	    mrec.setCamera(mCamera);
	    
	    mrec.setVideoSource(MediaRecorder.VideoSource.CAMERA);
	    mrec.setAudioSource(MediaRecorder.AudioSource.MIC); 
//	    mrec.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//	    mrec.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
//	    mrec.setAudioEncoder(MediaRecorder.VideoEncoder.DEFAULT);
	    mrec.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_LOW));
	    String savePath = Environment.getExternalStorageDirectory()+"/DCIM/Camera";
	    mrec.setOutputFile(savePath+"/video.mp4"); 
//	    mrec.setMaxDuration(50000); // 50 seconds
//	    mrec.setMaxFileSize(5000000); // Approximately 5 megabytes
//	    mrec.setVideoSize(720, 480);
	    //mrec.setVideoFrameRate(15);
	    //미리보기
	    mrec.setPreviewDisplay(surfaceHolder.getSurface());
	    mrec.prepare();
	}

	protected void startRecording() throws IOException {
	    mrec.start();
	}
	
	public void onClick(View v) {

	}
	
	protected void stopRecording() {
	    mrec.stop();
	    mrec.release();
	    mCamera.release();
	}

	private void releaseMediaRecorder(){
	    if (mrec != null) {
	        mrec.reset();   // clear recorder configuration
	        mrec.release(); // release the recorder object
	        mrec = null;
	        mCamera.lock();           // lock camera for later use
	    }
	}
	
	private void releaseCamera(){
	    if (mCamera != null){
	        mCamera.release();        // release the camera for other applications
	        mCamera = null;
	    }
	}
	

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	
	public void surfaceCreated(SurfaceHolder holder) {
	    if (mCamera != null){
	        Parameters params = mCamera.getParameters();
	        mCamera.setParameters(params);
	    }
	    else {
	        Toast.makeText(getApplicationContext(), "Camera not available!", Toast.LENGTH_LONG).show();
	        finish();
	    }
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		if(mrec!=null) mrec.release();
		mrec = null;
	    mCamera.stopPreview();
	    mCamera.release();
	    mCamera = null;
	}
}
