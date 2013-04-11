package com.example.uidesign;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;

public class sound extends Service {
	MediaPlayer mp;
	Thread thread;
	boolean flag;
	SharedPreferences pref_ringtones;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		flag = false;
		mp.stop();
		mp.release();
		thread.interrupt();
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		pref_ringtones = getSharedPreferences("selectringtones", 0);
		flag = true;
		switch(pref_ringtones.getInt("selectringtones",0))
		{
			case 0:
				mp = MediaPlayer.create(sound.this, R.raw.alien);
				break;
			case 1 :
				mp = MediaPlayer.create(sound.this, R.raw.bicycle);
				break;
			case 2 :
				mp = MediaPlayer.create(sound.this, R.raw.bounce);
				break;
			case 3 :
				mp = MediaPlayer.create(sound.this, R.raw.buzz);
				break;
			case 4 :
				mp = MediaPlayer.create(sound.this, R.raw.catchme);
				break;
			case 5 :
				mp = MediaPlayer.create(sound.this, R.raw.waltz);
				break;
			case 6 :
				mp = MediaPlayer.create(sound.this, R.raw.confession);
				break;
			case 7 :
				mp = MediaPlayer.create(sound.this, R.raw.horizon);
				break;
			case 8 :
				mp = MediaPlayer.create(sound.this, R.raw.curious);
				break;
			case 9 :
				mp = MediaPlayer.create(sound.this, R.raw.goodbye);
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
		thread = new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				while(flag){
					mp.setLooping(true);
					mp.start();
				}
			}
		};
		thread.start();
	}

}
