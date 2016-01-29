package com.example.music.adpter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> list;

	public MyPagerAdapter(FragmentManager manager){
		super(manager);
	}
	
	public MyPagerAdapter(FragmentManager fragmentManager,List<Fragment> list) {
		super(fragmentManager);
		this.list=list;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.isEmpty() ? 0 : list.size();
	}


}
