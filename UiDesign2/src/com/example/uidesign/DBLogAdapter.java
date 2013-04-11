package com.example.uidesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DBLogAdapter extends CursorAdapter {
	public DBLogAdapter(Context context, Cursor c){
		super(context,c);
	}
	
	public void bindView(View view, Context context, Cursor cursor){
		final TextView call_time = (TextView)view.findViewById(R.id.call_time);
		final TextView lat = (TextView)view.findViewById(R.id.lat);
		final TextView lon = (TextView)view.findViewById(R.id.lon);
		SharedPreferences pref_fonts;
		pref_fonts = context.getSharedPreferences("customFonts", 0);
        String fonts = pref_fonts.getString("customFonts", "fonts/DroidSans.ttf");
		
		Typeface face=Typeface.createFromAsset(context.getAssets(), fonts);
		
		call_time.setTypeface(face);
		lat.setTypeface(face);
		lon.setTypeface(face);
		
		call_time.setText("시간 : "+cursor.getString(cursor.getColumnIndex("call_time")));
		lat.setText("위도 : "+cursor.getString(cursor.getColumnIndex("lat")));
		lon.setText("경도 : "+cursor.getString(cursor.getColumnIndex("lon")));
	}
	
	
	public View newView(Context context, Cursor cursor, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.listlayout2, parent, false);
		return v;
	}
}
