package com.example.uidesign;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

public class mLogActivity extends Activity {
	String sql;
	Cursor cursor;
	ListView log_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_log);
        
        log_list = (ListView)findViewById(R.id.log_list);
        
        SQLiteOpenHelper dbHelper = new DBManager(mLogActivity.this, "reg.db");
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		sql = "select *from mLog;";
    	
	    cursor = db.rawQuery(sql, null);
	    if(cursor.getCount() > 0){
	     startManagingCursor(cursor);
	     DBLogAdapter dbLogAdapter = new DBLogAdapter(mLogActivity.this, cursor);
	     log_list.setAdapter(dbLogAdapter);
	    }
	    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_m_log, menu);
        return true;
    }

    
}
