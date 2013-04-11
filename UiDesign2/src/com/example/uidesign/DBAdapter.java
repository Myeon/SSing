package com.example.uidesign;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DBAdapter extends CursorAdapter {
	public DBAdapter(Context context, Cursor c){
		super(context,c);
	}
	
	public void bindView(View view, Context context, Cursor cursor){
		SharedPreferences pref_fonts;
		pref_fonts = context.getSharedPreferences("customFonts", 0);
        String fonts = pref_fonts.getString("customFonts", "fonts/DroidSans.ttf");
		
        Typeface face=Typeface.createFromAsset(context.getAssets(), fonts);
		
		final TextView rec_name = (TextView)view.findViewById(R.id.rec_name);
		final TextView phone_num = (TextView)view.findViewById(R.id.phone_num);
		//final TextView mes = (TextView)view.findViewById(R.id.message);
		phone_num.setTypeface(face);
		rec_name.setTypeface(face);
		
		phone_num.setText("번호 : "+cursor.getString(cursor.getColumnIndex("phone_num")));
		rec_name.setText("이름 : "+cursor.getString(cursor.getColumnIndex("rec_name")));
		//mes.setText(cursor.getString(cursor.getColumnIndex("message")));
	}
	
	
	public View newView(Context context, Cursor cursor, ViewGroup parent){
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.listlayout, parent, false);
		return v;
	}
}
