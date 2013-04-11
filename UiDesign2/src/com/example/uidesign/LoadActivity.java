// 로딩화면

package com.example.uidesign;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class LoadActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		
//		Handler handler = new Handler()
//		{
//			@Override
//			public void handleMessage(Message msg)
//			{
//				finish();
//			}
//		};
//		
//		handler.sendEmptyMessageDelayed(0, 2000);
	}
}
