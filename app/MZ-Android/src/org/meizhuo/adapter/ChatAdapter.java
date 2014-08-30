package org.meizhuo.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import org.meizhuo.app.R;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<HashMap<String,Object>> chatList = null;
	int[] layout;
	String[] from;
	int[] to;
	public final static int Me = 0 ;

	

	public ChatAdapter(Context context,ArrayList<HashMap<String,Object>> chatList,int[] layout,String[] from,int[] to) {
		// TODO Auto-generated constructor stub
		super();
		this.context = context;
		this.chatList = chatList;
		this.layout = layout;
		this.from = from;
		this.to  = to;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return chatList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return chatList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null ;
		int who =(Integer) chatList.get(position).get("person");
		
		convertView = LayoutInflater.from(context).inflate(layout[who==Me?0:1], null);
		holder = new ViewHolder();
		holder.imageView = (ImageView)convertView.findViewById(to[who*2+0]);
		holder.textView = (TextView) convertView.findViewById(to[who*2+1]);
			
		holder.imageView.setBackgroundResource((Integer)chatList.get(position).get(from[0]));
		holder.textView.setText(chatList.get(position).get(from[1]).toString());
		return convertView;
	}
	
	class ViewHolder
	{
		public ImageView imageView;
		public TextView textView;
	}
	
	

}
