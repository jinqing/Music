package com.example.music.fregament;


import java.io.Serializable;
import java.util.List;

import com.example.music.MusicActivity;
import com.example.music.MusicListActivity;
import com.example.music.R;
import com.example.music.bean.Mp3;
import com.example.music.util.MusicUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Myfregament extends Fragment implements OnClickListener{
	private List<Mp3> list_mp3;
	private RelativeLayout rl_bendi;
	private TextView tv_my_musicnum;
	private Setlist setlist;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.my, container, false);
		rl_bendi=(RelativeLayout)view.findViewById(R.id.rl_bendi);
		tv_my_musicnum=(TextView)view.findViewById(R.id.tv_my_musicnum);
		rl_bendi.setOnClickListener(this);
		
		list_mp3=MusicUtil.getAllSongs(getActivity());
		tv_my_musicnum.setText(list_mp3.size()+"首歌曲");
		
		return view;
	}
	
	public interface Setlist{
		public void setlist(List<Mp3> list);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.rl_bendi:
			Intent intent=new Intent(getActivity(),MusicListActivity.class);
			intent.putExtra("list",(Serializable)list_mp3);
			getActivity().startActivity(intent);
			break;

		default:
			break;
		}
	}
}
