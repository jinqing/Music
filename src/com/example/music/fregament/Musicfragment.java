package com.example.music.fregament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.music.bean.Mp3;
import com.example.music.fregament.Myfregament.Setlist;

import android.support.v4.app.Fragment;

public class Musicfragment extends Fragment{
	
	
	Setlist setlist=new Setlist() {
		@Override
		public void setlist(List<Mp3> list) {
			// TODO Auto-generated method stub
			List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", list.get(i).getSqlId());
				map.put("songName", list.get(i).getName());
				if (list.get(i).getSingerName().equals("<unknown>")) {
					map.put("singerName", "----");
				} else {
					map.put("singerName", list.get(i).getSingerName());
				}
				listItems.add(map);
			} 
		}
	};
	
}
