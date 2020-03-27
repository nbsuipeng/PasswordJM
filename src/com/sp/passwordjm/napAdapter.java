package com.sp.passwordjm;

import java.util.LinkedList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class napAdapter extends BaseAdapter {
	

	    private LinkedList<nap> mData;
	    private Context mContext;

	    public napAdapter(LinkedList<nap> mData, Context mContext) {
	        this.mData = mData;
	        this.mContext = mContext;
	    }
	    //��ȡ��������
	    @Override
	    public int getCount() {
	        return mData.size();
	    }
	    //��ȡһ������
	    @Override
	    public Object getItem(int position) {
	        return null;
	    }
	    //��ȡ����λ��
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	    	//��ȡ�����ļ�
	        convertView = LayoutInflater.from(mContext).inflate(R.layout.nap_list,parent,false);
	        //��ȡ�����ļ��еĿؼ�
	        TextView name = (TextView) convertView.findViewById(R.id.textView1);
	        TextView account = (TextView) convertView.findViewById(R.id.textView2);
	        TextView pwd = (TextView) convertView.findViewById(R.id.textView3);
	      //�������ļ��������
	        name.setText(mData.get(position).getName());
	        account.setText(mData.get(position).getAccount());
	        pwd.setText(mData.get(position).getPassword());
	        return convertView;
	    }

		
	
}
