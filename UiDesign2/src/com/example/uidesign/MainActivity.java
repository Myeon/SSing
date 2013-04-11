// 메인화면

package com.example.uidesign;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity 
{
	final int one = 0;
	final int two = 1;
	final int three = 2;
	final int four = 3;
	final int five = 4;
	final int six = 5;
	SharedPreferences pref_fonts;
	String fonts;
	final long FINISH_INTERVAL_TIME = 2000;
	long backPressedTime = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        startActivity(new Intent(this, LoadActivity.class));
        
        pref_fonts = getSharedPreferences("customFonts", 0);
        fonts = pref_fonts.getString("customFonts", "fonts/DroidSans.ttf");
        
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;
        
        Typeface face=Typeface.createFromAsset(getAssets(), fonts);
        
        
        
        intent = new Intent(this, Tab0Activity.class);
        spec = tabHost.newTabSpec("tab0")
        		.setIndicator("메인화면", res.getDrawable(android.R.drawable.ic_menu_compass))
        		.setContent(intent);
        tabHost.addTab(spec);
        
//        intent = new Intent(this, Tab1Activity.class);
//        spec = tabHost.newTabSpec("tab1")
//        		.setIndicator("ㅋㅋㅋㅋㅋㅋ", res.getDrawable(android.R.drawable.ic_menu_add))
//        		.setContent(intent);
//        tabHost.addTab(spec);
        
        intent = new Intent(this, Tab2Activity.class);
        spec = tabHost.newTabSpec("tab1")
        		.setIndicator("번호목록", res.getDrawable(android.R.drawable.ic_menu_search))
        		.setContent(intent);
        tabHost.addTab(spec);
        
//        intent = new Intent(this, Tab3Activity.class);
//        spec = tabHost.newTabSpec("tab2")
//        		.setIndicator("가상전화", res.getDrawable(android.R.drawable.ic_menu_call))
//        		.setContent(intent);
//        tabHost.addTab(spec);
        
        intent = new Intent(this, Tab4Activity.class);
        spec = tabHost.newTabSpec("tab2")
        		.setIndicator("설정", res.getDrawable(android.R.drawable.ic_menu_manage))
        		.setContent(intent);
        tabHost.addTab(spec);
        
        for (int i = 0; i < 3; i++) 
        {
			tabHost.getTabWidget().getChildAt(i).setBackgroundColor(0x00000000);
		}
        
        for(int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) 
        { 
        	TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
        	tv.setTextSize(10);
        	tv.setTypeface(face);
        	tv.setTextColor(Color.parseColor("#ffffff"));
        } 
        
//        TextView tv = (TextView) tabHost.getCurrentTabView().findViewById(android.R.id.title);
//        tv.setTextSize(10);
//        tv.setTypeface(face);
//        tv.setTextColor(Color.parseColor("#ffffff"));

        tabHost.setBackgroundColor(R.drawable.tabbar);
        tabHost.setCurrentTab(0);
    }
    
    

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) 
//    {
//        //getMenuInflater().inflate(R.menu.activity_main, menu);
//    	
//    	       return true;
//    }
//    
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu)
//    {
//		return super.onPrepareOptionsMenu(menu);
//    }
//    
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//    	switch(item.getItemId())
//    	{
//    	case one:
//    		Toast.makeText(MainActivity.this, "One", Toast.LENGTH_SHORT).show();
//    		break;
//    		
//    	case two:
//    		Toast.makeText(MainActivity.this, "Two", Toast.LENGTH_SHORT).show();
//    		break;
//    		
//    	case three:
//    		Toast.makeText(MainActivity.this, "Three", Toast.LENGTH_SHORT).show();
//    		break;
//    		
//    	case four:
//    		Toast.makeText(MainActivity.this, "굴림체", Toast.LENGTH_SHORT).show();
//    		
//    	case five:
//    		Toast.makeText(MainActivity.this, "궁서체", Toast.LENGTH_SHORT).show();
//    		
//    	case six:
//    		Toast.makeText(MainActivity.this, "돋움체", Toast.LENGTH_SHORT).show();
//    		
//    	default:
//    			break;
//    	}
//    	return super.onOptionsItemSelected(item);
//    }
//    
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)
//    {
//    	switch(keyCode)
//    	{
//    	case KeyEvent.KEYCODE_BACK:
//    		new AlertDialog.Builder(MainActivity.this)
//    		.setMessage("종료하시겠습니까?")
//    		.setPositiveButton("예", new DialogInterface.OnClickListener() {
//				
//				public void onClick(DialogInterface dialog, int which) {
//					moveTaskToBack(true);
//					finish();
//					System.exit(0);
//					android.os.Process.killProcess(android.os.Process.myPid());
//					
//					ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//					am.restartPackage(getPackageName());
//				}
//			}).setNegativeButton("아니오", null).show();
//    	}
//		return true;
//    }
}