package com.sp.passwordjm;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

@SuppressLint("SdCardPath")
public class RegisterActivity extends Activity {
	EditText et1;
	Button b1;
	Context mContext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		et1=(EditText)findViewById(R.id.passwordRegister);
		mContext = RegisterActivity.this;
		b1=(Button)findViewById(R.id.register);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String s=et1.getText().toString();
				//����Ƿ��ȡ�洢Ȩ��
				if(true)//��Ȩ��
				{
					storePassword(s);
					Intent i=new Intent(RegisterActivity.this,PasswordListActivity.class);
					i.putExtra("name", s.substring(0, 5));//0 1 2 3 4
					//�����������
					startActivity(i);
					finish();
				}
				
			}
		});
		
	}
	public void storePassword(String s) 
	{
		store(md5(s));
	}
	public String md5(String s)
	{
		return md5JM.encrypt(s);
	}
	public void store(String s) 
	{
		//ʹ�ô洢
		String path ="pwd.txt";;
		FileOutputStream fos = null;
		try {
			fos = getApplicationContext().openFileOutput(path, MODE_PRIVATE);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
