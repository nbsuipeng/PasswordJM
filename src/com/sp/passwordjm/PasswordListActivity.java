package com.sp.passwordjm;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class PasswordListActivity extends Activity {
	ListView lv1;
	Button b1;
	Button b2;
	String tableName;
	LinkedList<nap> info;
	//�ɵ����ģ�����������Ϣ
	LinkedList<nap> ll=new LinkedList<nap>(); 
	 class MyButtonlistener implements View.OnClickListener{
         @Override
         public void onClick(View v) {
             //����¼�����
        	 switch(v.getId())
        	 {
        	 case R.id.bar_btn:
        		//��������ת���˺���ӽ���
 				Intent i=new Intent(PasswordListActivity.this,AddPasswordActivity.class);
 				startActivity(i);
 				break;
        	 case R.id.flush_btn:
        		 
        		 updateList();
        		 Toast.makeText(PasswordListActivity.this, "ˢ�³ɹ�", Toast.LENGTH_SHORT).show();
        	        break;
 				
        	 }
         }
     }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.password_activity);
	info=new LinkedList<nap>();
	lv1=(ListView)findViewById(R.id.passwordList);
	Intent i=getIntent();
	tableName=i.getExtras().getString("name");
	//Toast.makeText(PasswordListActivity.this,tableName, Toast.LENGTH_LONG).show();
	//��ȡ�������������
	ActionBar actionBar = getActionBar();
    if (actionBar != null) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable�Զ����View
        actionBar.setCustomView(R.layout.pwdlistactionbar);  //���Զ���Ĳ��֣�
        b1=(Button)actionBar.getCustomView().findViewById(R.id.bar_btn);
        b2=(Button)actionBar.getCustomView().findViewById(R.id.flush_btn);
        MyButtonlistener listener=new MyButtonlistener();
        b1.setOnClickListener(listener);
       b2.setOnClickListener(listener);
       //��ListView���ô����¼�
//      lv1.setOnItemClickListener(new OnItemClickListener() {
//		
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//			// TODO Auto-generated method stub
//			Toast.makeText(PasswordListActivity.this,String.valueOf(arg2)+info.get(arg2).getName(), Toast.LENGTH_LONG).show();
//		}
//	});
      //����ɾ��
      lv1.setOnItemLongClickListener(new OnItemLongClickListener() {
		
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			// TODO Auto-generated method stub
			//Toast.makeText(PasswordListActivity.this,String.valueOf(arg2)+info.get(arg2).getName(), Toast.LENGTH_LONG).show();
			//ɾ��
			deleteOneRow(info.get(arg2).getName());
			//����
			updateList();
			return false;
		}
	});
        //��������
        updateList();
        
        
        
    }
    

    
	}
	//ɾ��ĳһ��
	public void deleteOneRow(String arg)
	{
		
        DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
        SQLiteDatabase db = dbh.getWritableDatabase();
        //���� ���� ����
        db.delete("mytable", "name=?", new String[]{arg});
        
	       
       
	}
	//������ͼ
	public void updateList()
	{
		 	info.clear();
	        DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
	        SQLiteDatabase db = dbh.getReadableDatabase();
	        Cursor result=db.rawQuery( 
	        	    "SELECT * FROM mytable", null);
	        result.moveToFirst(); 
	        while (!result.isAfterLast()) { 
	            String name=result.getString(0); 
	            String account=result.getString(1); 
	            String pwd=result.getString(2); 
	            
	            // do something useful with these 
	            info.add(new nap(name,account,pwd));
	            result.moveToNext(); 
	          } 
	          result.close();
	          db.close();
	       
	        napAdapter adapter=new napAdapter((LinkedList<nap>)info,PasswordListActivity.this);
	        lv1.setAdapter( adapter);
	        
	}

}
