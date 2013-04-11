package com.example.uidesign;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View.OnClickListener;

public class DBManager extends SQLiteOpenHelper{
	
	
	public DBManager(Context context, String name) {
		super(context, name, null, 1);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table members (_id integer primary key autoincrement, phone_num text, rec_name text);");
		db.execSQL("create table mLog (_id integer primary key autoincrement, call_time text, lat text, lon text);");
//		db.execSQL("create table message (_id integer primary key autoincrement, message text);");
//		db.execSQL("create table setting (_id integer primary key autoincrement, name text, number text, message text);");
//		db.execSQL("insert into mLog values(null, '미등록', '미등록', '미등록');");
//		db.execSQL("insert into message values(null, 'Help me!!!');");
//		db.execSQL("insert into message values(null, '긴급상황 입니다. 도와주세요.');");
//		db.execSQL("insert into message values(null, '빨리 제위치로 와주세요!!');");
//		db.execSQL("insert into message values(null, '당신의 도움이 필요합니다.');");
//		db.execSQL("insert into message values(null, '위급합니다. 제위치로 와주세요!!');");
				
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("drop table members;");
		onCreate(db);

		}
}
