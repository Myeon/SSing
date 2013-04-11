// 첫번째 탭 -> 메인화면과 동일함. 해당 탭을 누르면 다른 탭에서 메인화면으로 이동.

package com.example.uidesign;

import android.annotation.SuppressLint;
import android.app.Activity; 
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import android.util.Log;
import android.view.*;

@SuppressLint("ParserError")
public class Tab0Activity extends Activity {
	ImageView ib, ib1, ib2;

	private boolean flag;
 @Override
    public void onCreate(Bundle savedInstanceState) 
 	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maindetail);

        ib1 = (ImageView)findViewById(R.id.imageView1);
        ib2 = (ImageView)findViewById(R.id.imageView2);
        ib1.setClickable(true);
        ib2.setClickable(true);
        
        ib1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Context context = getApplicationContext();
            	Intent intent = new Intent(context, SendActivity.class);
            	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            	context.startActivity(intent);
            	flag = false;
			}
		});
        
        ib2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Tab0Activity.this, VirtureActivity.class);
		        startActivity(intent);				
			}
		});
        
    }
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	stopService(new Intent("com.sound"));
}

}