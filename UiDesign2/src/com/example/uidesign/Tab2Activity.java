package com.example.uidesign;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity; 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Tab2Activity extends Activity 
{
	String sql, phone_num;
	Cursor cursor;
	ListView lv;
	ArrayList addList = new ArrayList();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2);
        
        Typeface face=Typeface.createFromAsset(getAssets(), "fonts/DroidSans.ttf");
        lv = (ListView)findViewById(R.id.listView1);
       
        SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		sql = "select *from members;";
	    	
	    cursor = db.rawQuery(sql, null);
	    if(cursor.getCount()>0){
	     startManagingCursor(cursor);
	     DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
	     lv.setAdapter(dbAdapter);
	    }
       
	    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				cursor.moveToPosition(position);
				// TODO Auto-generated method stub
				AlertDialog alert = new AlertDialog.Builder(Tab2Activity.this)
                // 메시지를 설정한다.
                .setMessage("삭제하시겠습니까?")
                // positive 버튼을 설정한다.
                .setPositiveButton("확인", new DialogInterface.OnClickListener()
                {
                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
                    public void onClick(DialogInterface dialog, int which)
                    {
                    	
                    	phone_num = cursor.getString(cursor.getColumnIndex("phone_num"));
                    	
                    	SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
                		SQLiteDatabase db = dbHelper.getWritableDatabase();
                		
                		sql = "delete from members where phone_num = '"+phone_num+"';";
                		db.execSQL(sql);
                        // 다이얼로그를 화면에서 사라지게 한다.
                        dialog.dismiss();
//                                  			
            			sql = "select *from members;";
            	    	
            		    cursor = db.rawQuery(sql, null);
            		    if(cursor.getCount() > 0){
            		     startManagingCursor(cursor);
            		     DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
            		     lv.setAdapter(dbAdapter);
            		    }
                        Toast.makeText(Tab2Activity.this, "삭제하였습니다.", Toast.LENGTH_LONG).show();
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
				return false;
			}
		});
        
    }
		
	  @Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			SQLiteOpenHelper dbHelper = new DBManager(Tab2Activity.this, "reg.db");
			SQLiteDatabase db = dbHelper.getWritableDatabase();
			sql = "select *from members;";
		    cursor = db.rawQuery(sql, null);
		    
		    if(cursor.getCount()>0){
		    	 startManagingCursor(cursor);
		    	 DBAdapter dbAdapter = new DBAdapter(Tab2Activity.this, cursor);
		    	 lv.setAdapter(dbAdapter);
			}else if(cursor.getCount() == 0){
				
			}
		}
 	public boolean onCreateOptionsMenu(Menu menu) {
 		menu.add(0, 0, 0, "등록");
// 		menu.add(0, 1, 0, "삭제");
 		
 		return true;
 	}
 	
 	public boolean onOptionsItemSelected(MenuItem item){
    	switch (item.getItemId()){
    	case 0:
    		Intent intent = new Intent(Tab2Activity.this, PhoneAddressActivity.class);
        	startActivity(intent);
    		break;
//    	case 1:
//    		Intent intent2 = new Intent(Tab2Activity.this, DeleteAddressActivity.class);
//        	startActivity(intent2);
//    		break;
    	
    	}
    	return true;
    }
}
