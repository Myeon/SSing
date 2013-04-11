//// 가상전화 화면, 클릭 시에 벨소리 재생 및 중단은 chgView 메서드의 if-else 문에 구현하면 됨
//
//package com.example.uidesign;
//
//import android.app.Activity; 
//import android.content.Intent;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class Tab3Activity extends Activity implements View.OnClickListener
//{
//	
//	ImageView img;
//	Drawable a1, a2;
//	int cnt;
//	
// @Override
//    public void onCreate(Bundle savedInstanceState) 
// 	{
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tab3);
//        
//        cnt = 0;
//        img = (ImageView)findViewById(R.id.imageView1);
//        a1 = this.getResources().getDrawable(R.drawable.call);
//        a2 = this.getResources().getDrawable(R.drawable.incall);
//        img.setClickable(true);
//        img.setOnClickListener(this);
//        
//        
////        TextView textView = new TextView(this);
////        textView.setText("가상전화");
////        
////        setContentView(textView);
//    }
//
// 	public void onClick(View v) 
// 	{
// 		chgView();
// 	}
// 	
// 	private void chgView()
// 	{
// 		if(cnt == 0)
// 		{
// 			img.setImageDrawable(a1);
// 			cnt = 1;
// 			System.out.println(cnt);
// 		}
// 		
// 		else if(cnt == 1)
// 		{
// 			img.setImageDrawable(a2);
// 			cnt = 0;
// 			System.out.println(cnt);
// 		}
// 	}
//}