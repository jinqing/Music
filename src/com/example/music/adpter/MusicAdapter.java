package com.example.music.adpter;

import java.util.List;
import java.util.zip.Inflater;

import com.example.music.MusicActivity;
import com.example.music.R;
import com.example.music.bean.Mp3;
import com.example.music.service.MusicService;
import com.example.music.service.MusicService.LocalBinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MusicAdapter extends BaseAdapter{
	List<Mp3> list;
	Context context;
//	Mp3 mp3;
	
	
	public MusicAdapter(List<Mp3> list,Context context){
		this.list=list;
		this.context=context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final Mp3 mp3=list.get(position);
		
		Music_item music_item;
		if(convertView==null){
			music_item=new Music_item();
			convertView=LayoutInflater.from(context).inflate(R.layout.music_item,null);
			music_item.music_num=(TextView)convertView.findViewById(R.id.tv_music_num);
			music_item.music_name=(TextView)convertView.findViewById(R.id.tv_music_name);
			music_item.music_singer=(TextView)convertView.findViewById(R.id.tv_music_singer);
			convertView.setTag(music_item);
		}else music_item=(Music_item)convertView.getTag();
		
		music_item.music_num.setText(String.valueOf(position+1)+".");
		music_item.music_name.setText(mp3.getName());
		music_item.music_singer.setText(mp3.getSingerName());
		
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(context,MusicService.class);
				intent.putExtra("music_url", mp3.getUrl());
				context.startService(intent);
				// TODO Auto-generated method stub
//				Intent intent=new Intent(context,MusicActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				context.startActivity(intent);
//				((Activity) context).finish();
			}
		});
		
		return convertView;
	}
	
	
	
	class Music_item{
		TextView music_num,music_name,music_singer;
	}
}
