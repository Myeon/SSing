// 설정화면

package com.example.uidesign;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity; 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class Tab4Activity extends Activity 
{
	Button btn1;
	CheckBox cb1, cb3;
	TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
	String str, str_fonts;
	String fonts;
	String[] items;
	String[] font;
	String[] delay;
	SharedPreferences pref_msg;
	SharedPreferences pref_position;
	SharedPreferences pref_sound;
	SharedPreferences pref_fonts;
	SharedPreferences pref_fontsPosition;
	SharedPreferences pref_ringtones;
	
	ArrayList list = new ArrayList();
	ArrayList fontList = new ArrayList();
	ArrayList ringList = new ArrayList();
	int pre_pos, pre_sound, pre_font, pre_ring;
	Thread thread;
	MediaPlayer mp;
	boolean play=false;
	boolean ring=false;
 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab4);
        
        pref_fonts = getSharedPreferences("customFonts", 0);
   		fonts = pref_fonts.getString("customFonts", "fonts/DroidSans.ttf");
		
   		Typeface face=Typeface.createFromAsset(getAssets(), fonts);
   		
   		
     	       
        boolean flag = false;
        tv1 = (TextView)findViewById(R.id.textView1);
        cb1 = (CheckBox)findViewById(R.id.checkBox1);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        cb3 = (CheckBox)findViewById(R.id.checkBox3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);
        tv7 = (TextView)findViewById(R.id.textView7);
        tv8 = (TextView)findViewById(R.id.textView8);
        
        tv1.setTypeface(face);
   		tv2.setTypeface(face);
     	tv3.setTypeface(face);
     	tv4.setTypeface(face);
     	tv5.setTypeface(face);
     	tv6.setTypeface(face);
     	tv7.setTypeface(face);
     	tv8.setTypeface(face);
     	chkGpsService();
     	
     	tv8.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				
				String[] ring_list = {"외계인", "자전거 여행자", "바운스", "따르릉 송", "날 잡아 봐", "봄의 왈츠", "너에게", "지평선", "호기심 속닥속닥", "이젠안녕"};
				AlertDialog alert = new AlertDialog.Builder(Tab4Activity.this)
                // 메시지를 설정한다.
                .setTitle("벨소리 설정")
                .setSingleChoiceItems(ring_list, pre_ring, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {
						// TODO Auto-generated method stub
						if(ring == true){
							  mp.stop();
							  mp.release();
							  thread.interrupt();
							  ring = false;
						}
						pre_ring = position;
						switch(pre_ring)
						  {
						  case 0:
							 if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.alien);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 1 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.bicycle);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 2 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.bounce);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 3 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.buzz);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 4 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.catchme);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 5 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.waltz);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 6 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.confession);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 7 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.horizon);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 8 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.curious);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  case 9 :
							  if(ring == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  ring = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.goodbye);
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
									super.run();
									mp.start();
								}
							};
							ring = true;
							thread.start();	
							  break;
						  }
						 			 
					}
				})
            
                // positive 버튼을 설정한다.
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
                    public void onClick(DialogInterface dialog, int which)
                    {

                    	if(ring == true){
                    		mp.stop();
            				mp.release();
            				thread.interrupt();
            				ring = false;	
                    	}
                	
                    	SharedPreferences.Editor edit = pref_ringtones.edit();     				
                    	edit = pref_ringtones.edit();
                    	edit.putInt("selectringtones", pre_ring);
                    	edit.commit();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(ring == true){
                    		mp.stop();
            				mp.release();
            				thread.interrupt();
            				ring = false;	
                    	}
						 dialog.dismiss();
					}
				})
                // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
                .show();
			}
		});

        tv7.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				if(getStringArrayPref(Tab4Activity.this, "fontsList").size() == 0){
					fontList.add("fonts/DroidSans.ttf");
					fontList.add("fonts/Muhan.ttf");
					fontList.add("fonts/ASee.ttf");
					fontList.add("fonts/NanumPen.ttf");
					fontList.add("fonts/Tears.ttf");
					setStringArrayPref(Tab4Activity.this, "fontsList", fontList);
		        }
				
				fontList = getStringArrayPref(Tab4Activity.this, "fontsList");
				font = (String[]) fontList.toArray(new String[fontList.size()]);
				String[] font_list = {"기본서체", "무한도전", "밝은", "흘림", "눈물"};
				AlertDialog alert = new AlertDialog.Builder(Tab4Activity.this)
                // 메시지를 설정한다.
                .setTitle("글꼴 설정")
                .setSingleChoiceItems(font_list, pre_font, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {
						// TODO Auto-generated method stub
							str_fonts = font[position];
							pre_font = position;
//							Toast.makeText(Tab4Activity.this, str_fonts, Toast.LENGTH_LONG).show();
					}
				})
            
                // positive 버튼을 설정한다.
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
                    public void onClick(DialogInterface dialog, int which)
                    {
//                    	pref_fonts = getSharedPreferences("customFonts", 0);
                    	SharedPreferences.Editor edit = pref_fonts.edit();
        				edit.putString("customFonts", str_fonts);
        				edit.commit();
//        				pref_fontsPosition = getSharedPreferences("fontsPosition", 0);
						edit = pref_fontsPosition.edit();
        				edit.putInt("fontsPosition", pre_font);
        				edit.commit();
                    	//Toast.makeText(Tab4Activity.this, "값", Toast.LENGTH_LONG).show();
        				Intent intent = new Intent(Tab4Activity.this, MainActivity.class);
                    	startActivity(intent);
                    	finish();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 dialog.dismiss();
					}
				})
                // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
                .show();
				
			}
		});
 
        tv6.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent = new Intent(Tab4Activity.this, SettingActivity.class);
            	startActivity(intent);
			}
		});
        
        
        tv4.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View arg0) {
//				Intent intent = new Intent(Tab4Activity.this, SelectSound.class);
//				startActivity(intent);
				CharSequence[] sounds = {"없음", "사이렌1", "사이렌2", "소방차 사이렌", "여자 비명", "남자 비명", "경찰차 사이렌", "응급차 사이렌", "알람 소리"};
				AlertDialog alert = new AlertDialog.Builder(Tab4Activity.this)
                // 메시지를 설정한다.
                .setTitle("소리설정")
                .setSingleChoiceItems(sounds, pre_sound, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {
						// TODO Auto-generated method stub
						
						if(play == true){
							  mp.stop();
							  mp.release();
							  thread.interrupt();
							  play = false;
						}
						pre_sound = position;
						//눌렀을 때 소리나게
						 switch(pre_sound)
						  {
						  case 0:
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  break;
						  case 1 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.emer_alarm);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 2 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.emer_alarm2);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 3 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.mopp);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 4 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.scream);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 5 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.scream2);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 6 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.siren);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 7 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.siren2);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  case 8 :
							  if(play == true){
								  mp.stop();
								  mp.release();
								  thread.interrupt();
								  play = false;
							  }
							  mp = MediaPlayer.create(Tab4Activity.this, R.raw.smoke_alarm);
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
									super.run();
									mp.start();
								}
							};
							play = true;
							thread.start();	
							  break;
						  }
						 			 
					}
				})
            
                // positive 버튼을 설정한다.
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
                    public void onClick(DialogInterface dialog, int which)
                    {
                    	if(play == true){
                    		mp.stop();
                			mp.release();
                			thread.interrupt();
                			play = false;	
                    	}
                    	
//                    	pref_sound = getSharedPreferences("selectsound", 0);
                    	SharedPreferences.Editor edit = pref_sound.edit();     				
						edit = pref_sound.edit();
        				edit.putInt("selectsound", pre_sound);
        				edit.commit();
                    	
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if(play == true){
                    		mp.stop();
                			mp.release();
                			thread.interrupt();
                			play = false;
                    	}
            			dialog.dismiss();
					}
				})
                // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
                .show();
			}
		});
        
        tv5.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent intent = new Intent(Tab4Activity.this, mLogActivity.class);
				startActivity(intent);
			}
		});

        tv2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
//				Intent intent = new Intent(Tab4Activity.this, SelectMessage.class);
//				startActivity(intent);
				if(getStringArrayPref(Tab4Activity.this, "message").size() == 0){
		        	list.add("도와주세요!!!!");
		            setStringArrayPref(Tab4Activity.this, "message", list);
		        }
				list = getStringArrayPref(Tab4Activity.this, "message");
				items = (String[]) list.toArray(new String[list.size()]);
				
				AlertDialog alert = new AlertDialog.Builder(Tab4Activity.this)
                // 메시지를 설정한다.
                .setTitle("메세지 설정")
                .setSingleChoiceItems(items, pre_pos, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int position) {
						// TODO Auto-generated method stub
							str = items[position];
							pre_pos = position;
//							Toast.makeText(Tab4Activity.this, str, Toast.LENGTH_LONG).show();
					}
				})
            
                // positive 버튼을 설정한다.
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
                    public void onClick(DialogInterface dialog, int which)
                    {
                    	pref_msg = getSharedPreferences("messageText", 0);
                    	SharedPreferences.Editor edit = pref_msg.edit();
        				edit.putString("messageText", str);
        				edit.commit();
        				pref_position = getSharedPreferences("Position", 0);
						edit = pref_position.edit();
        				edit.putInt("Position", pre_pos);
        				edit.commit();
                    	//Toast.makeText(Tab4Activity.this, "값", Toast.LENGTH_LONG).show();
                    }
                })
                .setNeutralButton("생성", new DialogInterface.OnClickListener(){
                	public void onClick(DialogInterface dialog, int which){
                		Intent intent = new Intent(Tab4Activity.this, CreateMessage.class);
                    	startActivity(intent);
                	}
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 dialog.dismiss();
					}
				})
                // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
                .show();
            }

		});
        
        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getSharedPreferences("option", 0);
				SharedPreferences.Editor edit = pref.edit();
				boolean serviceFlag;
				
				if(cb1.isChecked()) {
					serviceFlag = true;
				} else
					serviceFlag = false;
				
								
				edit.putBoolean("serviceFlag", serviceFlag);
				edit.commit();
				if (arg1 == true) {
					startService(new Intent("com.example.uidesign"));
				}else if(arg1 == false) {
					stopService(new Intent("com.example.uidesign"));
				}
			}
        	
        });
        
//        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener(){
//
//			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
//				// TODO Auto-generated method stub
//				SharedPreferences pref = getSharedPreferences("option", 0);
//				SharedPreferences.Editor edit = pref.edit();
//				boolean messageFlag;
//
//				if(cb2.isChecked()) {
//					messageFlag = true;
//				}else
//					messageFlag = false;
//
//				edit.putBoolean("messageFlag", messageFlag);
//				edit.commit();
//	
//			}
//        	
//        });
        
        cb3.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				SharedPreferences pref = getSharedPreferences("option", 0);
				SharedPreferences.Editor edit = pref.edit();
			
				boolean cameraFlag;
			
				
				if (cb3.isChecked()) {
					cameraFlag = true;
				} else
					cameraFlag = false;
				
			
				edit.putBoolean("cameraFlag", cameraFlag);
				
				edit.commit();
			}
        	
        });
 	}


 
 public static ArrayList<String> getStringArrayPref(Context context, String key) {
     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
     String json = prefs.getString(key, null);
     ArrayList<String> urls = new ArrayList<String>();
     if (json != null) {
         try {
             JSONArray a = new JSONArray(json);
             for (int i = 0; i < a.length(); i++) {
                 String url = a.optString(i);
                 urls.add(url);
             }
         } catch (JSONException e) {
             e.printStackTrace();
         }
     }
     return urls;
 }
 
 private boolean chkGpsService() {
	 
 	String gs = android.provider.Settings.Secure.getString(getContentResolver(),
   	android.provider.Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
  
 	if (gs.indexOf("gps", 0) < 0) {
 		// GPS OFF 일때 Dialog 띄워서 설정 화면으로 튀어봅니다..
 		AlertDialog.Builder gsDialog = new AlertDialog.Builder(this);
 		gsDialog.setTitle("GPS 꺼져있습니다.");
 		gsDialog.setMessage("설정화면으로 이동합니다.");
 		gsDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
  
 			public void onClick(DialogInterface dialog, int which) {
   				// GPS설정 화면으로 튀어요
   				Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
   				intent.addCategory(Intent.CATEGORY_DEFAULT);
  				startActivity(intent);
 			}
 		}).create().show();
 		return false;
 	} else {
 		return true;
 	}
}
 
 
 
 public static void setStringArrayPref(Context context, String key, ArrayList<String> values) {
     SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
     SharedPreferences.Editor editor = prefs.edit();
     JSONArray a = new JSONArray();
     for (int i = 0; i < values.size(); i++) {
         a.put(values.get(i));
     }
     if (!values.isEmpty()) {
         editor.putString(key, a.toString());
     } else {
         editor.putString(key, null);
     }
     editor.commit();
 }
 
 @Override
 	protected void onResume() {
   		super.onResume();
   		
   		SharedPreferences pref = getSharedPreferences("option", 0);
    	cb1.setChecked(pref.getBoolean("serviceFlag", false));
    	//cb2.setChecked(pref.getBoolean("messageFlag", false));
    	cb3.setChecked(pref.getBoolean("cameraFlag", false));
    	pref_sound = getSharedPreferences("selectsound", 0);
    	pre_sound = pref_sound.getInt("selectsound", 0);
    	pref_position = getSharedPreferences("Position", 0);
    	pre_pos = pref_position.getInt("Position", 0);
    	pref_fontsPosition = getSharedPreferences("fontsPosition", 0);
    	pre_font = pref_fontsPosition.getInt("fontsPosition", 0);
    	pref_ringtones = getSharedPreferences("selectringtones", 0);
    	pre_ring = pref_ringtones.getInt("selectringtones", 0);
    }

    @Override
    protected void onPause() {
      		super.onPause();
    		SharedPreferences pref = getSharedPreferences("option", 0);
    		SharedPreferences.Editor edit = pref.edit();
    		boolean serviceFlag;
    		boolean cameraFlag;
    		boolean messageFlag;
    		
    		if(cb1.isChecked()) {
    			serviceFlag = true;
    		} else
    			serviceFlag = false;
    		
//    		if(cb2.isChecked()) {
//    			messageFlag = true;
//    		}else
//    			messageFlag = false;
    		
    		if (cb3.isChecked()) {
    			cameraFlag = true;
    		} else
    			cameraFlag = false;
    		
    		edit.putBoolean("serviceFlag", serviceFlag);
    		edit.putBoolean("cameraFlag", cameraFlag);
    		//edit.putBoolean("messageFlag", messageFlag);
    		edit.commit();
    	}

    	@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.activity_main, menu);
            return true;
        }

        
}

