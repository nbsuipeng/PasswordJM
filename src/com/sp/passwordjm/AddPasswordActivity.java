package com.sp.passwordjm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class AddPasswordActivity extends Activity {
	EditText name;
	EditText account;
	EditText password;
	Button store;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_password);
		name=(EditText)findViewById(R.id.editText1);
		account=(EditText)findViewById(R.id.editText2);
		password=(EditText)findViewById(R.id.editText3);
		store=(Button)findViewById(R.id.button1);
		store.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//�洢����
				storePassword(name.getText().toString(),account.getText().toString(),password.getText().toString());
				//��ʾ
				Toast.makeText(AddPasswordActivity.this, "��ӳɹ�", Toast.LENGTH_SHORT).show();;
				//���
				name.setText("");
				account.setText("");
				password.setText("");
			}
		});
	}
	//ʹ��sqlite�洢����
	public void storePassword(String n,String a,String p)
	{
		//�ж��Ƿ�δ����ͱ���
		if(n.length()==a.length()&&n.length()==p.length())
		{
			Toast.makeText(AddPasswordActivity.this, "���벻��ȫΪ��", Toast.LENGTH_SHORT).show();
		}else
		{
			DataBaseHelper dbh=new DataBaseHelper(getApplicationContext(), "pwd",null,1);
			SQLiteDatabase db = dbh.getWritableDatabase();
			//��������
			ContentValues cv=new ContentValues(); 
			cv.put("name", n); 
			cv.put("account", a); 
			cv.put("password", p); 
			db.insert("mytable", "", cv);
		}
		
		
	}
	//��������
	public String jiami(String p)
	{
		return AESUtil.AESJiaMi(p);
	}

}
