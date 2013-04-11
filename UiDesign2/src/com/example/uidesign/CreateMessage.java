package com.example.uidesign;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class CreateMessage extends Activity {
	Button rec_msg, reset_msg;
	EditText create_msg;
	ArrayList msg_list = new ArrayList();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);
        
        rec_msg = (Button)findViewById(R.id.reg_msg);
        reset_msg = (Button)findViewById(R.id.reset_msg);
        create_msg = (EditText)findViewById(R.id.create_msg);
        
        rec_msg.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String msg = create_msg.getText().toString();
				msg_list = getStringArrayPref(CreateMessage.this, "message");
				msg_list.add(msg);
				setStringArrayPref(CreateMessage.this, "message", msg_list);
				finish();
			}
		});
        
        reset_msg.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			finish();	
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
        }else{
         	String url = "도와주세요!!!!"; 
    			urls.add(url);
    	}
        return urls;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_message, menu);
        return true;
    }

    
}
