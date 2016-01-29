package com.example.music;

import java.util.List;

import com.example.music.adpter.MusicAdapter;
import com.example.music.bean.Mp3;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MusicListActivity extends Activity {
	private List<Mp3> list;
	private LinearLayout ly_back;
	private ListView lv_musiclist;
	private MusicAdapter musicadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_list);
		
		ly_back=(LinearLayout)findViewById(R.id.ly_back);
		lv_musiclist=(ListView)findViewById(R.id.lv_musiclist);
		ly_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		list=(List<Mp3>)getIntent().getSerializableExtra("list");
		musicadapter=new MusicAdapter(list, this);
		lv_musiclist.setAdapter(musicadapter);
	}
}
