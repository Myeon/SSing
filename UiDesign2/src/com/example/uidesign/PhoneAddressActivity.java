package com.example.uidesign;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PhoneAddressActivity extends Activity {
	  ListView listPerson;
	  ArrayList listNumber = new ArrayList();
	  ArrayList listName = new ArrayList();
//	  ArrayList persons = new ArrayList();
	  ArrayList uNumber = new ArrayList();
	  ArrayList uName = new ArrayList();
	  ArrayList searchList = new ArrayList();
	  ArrayList searchListNum = new ArrayList();
	  ArrayList check = new ArrayList();
	  
	  boolean searchTrue;
	  Button btn;
	  String sql;
	  EditText edit;
	  Button search_btn;
	  
	

		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_phone_address);
	        btn = (Button)findViewById(R.id.btn_add);
	        listPerson = (ListView)findViewById(R.id.list_view);
	        getList();
	        searchTrue = false;
	        search_btn = (Button)findViewById(R.id.search_btn);
	        edit = (EditText)findViewById(R.id.search_edit);
	        
	        listPerson.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	        
	        btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					SQLiteOpenHelper dbHelper = new DBManager(PhoneAddressActivity.this, "reg.db");
					SQLiteDatabase db = dbHelper.getWritableDatabase();
								        
			       
			        if(searchTrue == true){
			        	//검색했을때 listview
			        	for(int i=0; i<searchList.size(); i++){
							if(listPerson.isItemChecked(i) == true){
								listName.add(searchList.get(i).toString());
								listNumber.add(searchListNum.get(i).toString());
							}
						}
						 for(int i=0; i<listName.size(); i++){
					    		sql = "insert into members values(null, '"+listNumber.get(i).toString()+"', '"+listName.get(i).toString()+"');";
					    		db.execSQL(sql);
					    }
			        }else{
			        	//검색 안했을 때 listview
			        	for(int i=0; i<uName.size(); i++){
							if(listPerson.isItemChecked(i) == true){
								listName.add(uName.get(i).toString());
								listNumber.add(uNumber.get(i).toString());
							}
						}
						 for(int i=0; i<listName.size(); i++){
					    		sql = "insert into members values(null, '"+listNumber.get(i).toString()+"', '"+listName.get(i).toString()+"');";
					    		db.execSQL(sql);
					    }
			        }
					
			    	finish();
				}
			});
	        
	        search_btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					String search;
					search = edit.getText().toString();
					if(search.equals(null)){//검색창에 아무것도 입력안했을 때
						searchTrue = false;
						Toast.makeText(PhoneAddressActivity.this, "null", Toast.LENGTH_LONG).show();
						ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, uName);
						//리스트뷰에 표시
						listPerson.setAdapter(adp);
					}else{
						searchTrue = true;
						for(int i=0; i<uName.size(); i++){
							if(search.equals(uName.get(i).toString())){
								searchList.add(uName.get(i).toString());
								searchListNum.add(uNumber.get(i).toString());
							}
						}
						if(searchList.size() == 0){
							Toast.makeText(PhoneAddressActivity.this, "검색결과가 없습니다.", Toast.LENGTH_LONG).show();
							searchTrue = false;
						}else{
							ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, searchList);
							//리스트뷰에 표시
							listPerson.setAdapter(adp);
						}
					}
				}
			});
	        
//	        listPerson.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//
//	        	public void onItemClick(AdapterView<?> parent, View view, int position, long id){
//	        		
//	        			
//	        	}
//	        });
	        
	        listPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					if(searchTrue == true){
						AlertDialog alert = new AlertDialog.Builder(PhoneAddressActivity.this)
		                // 메시지를 설정한다.
		                .setTitle(searchList.get(position).toString())
		                .setMessage(searchListNum.get(position).toString())
		                
		                // positive 버튼을 설정한다.
		                .setPositiveButton("확인", new DialogInterface.OnClickListener()
		                {
		                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                    	dialog.dismiss();
		                    }
		                }).show();
					}else{
						AlertDialog alert = new AlertDialog.Builder(PhoneAddressActivity.this)
		                // 메시지를 설정한다.
		                .setTitle(uName.get(position).toString())
		                .setMessage(uNumber.get(position).toString())
		                
		                // positive 버튼을 설정한다.
		                .setPositiveButton("확인", new DialogInterface.OnClickListener()
		                {
		                    // positive 버튼에 대한 클릭 이벤트 처리를 구현한다.
		                    public void onClick(DialogInterface dialog, int which)
		                    {
		                    	dialog.dismiss();
		                    }
		                }).show();
					}
					
	                
	                
	                // 빌더를 통해 만든 알럿 다이얼로그를 화면에 보여준다.
	                 
					
					return false;
				}
	        	
			});
	        
	       
	    }
	    
	    
	    public void getList(){
	        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	        String[] projection = new String[] {           
	               ContactsContract.CommonDataKinds.Phone.NUMBER,
	               ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
	       };
	         
	       String[] selectionArgs = null;
	       //정렬
	       String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
	        //조회해서 가져온다
	       Cursor contactCursor = managedQuery(uri, projection, null, selectionArgs, sortOrder);
	       //정보를 담을 array 설정
	       if(contactCursor.moveToFirst()){       
	    	   do{
//	    		   persons.add(contactCursor.getString(1)); 
	               uNumber.add(contactCursor.getString(0));
	               uName.add(contactCursor.getString(1));
	           }while(contactCursor.moveToNext());

	       }
	       //리스트에 연결할 adapter 설정      
	       ArrayAdapter adp = new ArrayAdapter(PhoneAddressActivity.this, android.R.layout.simple_list_item_multiple_choice, uName);
	       //리스트뷰에 표시
	       listPerson.setAdapter(adp);
	   }
}