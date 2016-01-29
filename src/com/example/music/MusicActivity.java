package com.example.music;

import java.util.ArrayList;

import com.example.music.adpter.MyPagerAdapter;
import com.example.music.fregament.Fenleifragment;
import com.example.music.fregament.Myfregament;
import com.example.music.fregament.Paihangfragment;
import com.example.music.fregament.Tuijianfragment;
import com.example.music.service.MusicService;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicActivity extends FragmentActivity implements OnClickListener{
	private TextView tv_my,tv_tuijian,tv_paihang,tv_fenlei;
	private ImageView img_play,img_next;
	private ViewPager viewpager;
	private Context context;
	
	MusicService musicService;
	FragmentManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		context=this;
		init();
		stratservice();
		initviewpager();
	}
	
	void init(){
		
		tv_my=(TextView)findViewById(R.id.tv_my);
		tv_tuijian=(TextView)findViewById(R.id.tv_tuijian);
		tv_paihang=(TextView)findViewById(R.id.tv_paihang);
		tv_fenlei=(TextView)findViewById(R.id.tv_fenlei);
		img_play=(ImageView)findViewById(R.id.img_play);
		img_next=(ImageView)findViewById(R.id.img_next);
		
		tv_my.setOnClickListener(this);
		tv_tuijian.setOnClickListener(this);
		tv_paihang.setOnClickListener(this);
		tv_fenlei.setOnClickListener(this);
		img_play.setOnClickListener(this);
		img_next.setOnClickListener(this);
	}

	void stratservice(){
		Intent intent=new Intent(context,MusicService.class);
		startService(intent);
//		bindService(intent, connect, Context.BIND_AUTO_CREATE);
	}
	
	private ServiceConnection connect = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName className, IBinder service) {
			musicService = ((MusicService.LocalBinder) service).getService();//用绑定方法启动service，就是从这里绑定并得到service，然后就可以操作service了
		}
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
		}
	};
	
	public MusicService getservice(){
		return musicService;
	}
	
	@SuppressWarnings("deprecation")
	void initviewpager(){
		viewpager=(ViewPager)findViewById(R.id.viewpage);
		ArrayList<Fragment> list = new ArrayList<Fragment>();
		list.add(new Myfregament());
		list.add(new Tuijianfragment());
		list.add(new Paihangfragment());
		list.add(new Fenleifragment());
		manager=getSupportFragmentManager();
        viewpager.setAdapter(new MyPagerAdapter(manager,list));
        viewpager.setCurrentItem(0);
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_my:
			viewpager.setCurrentItem(0);
			break;
		case R.id.tv_tuijian:
			viewpager.setCurrentItem(1);
			break;
		case R.id.tv_paihang:
			viewpager.setCurrentItem(2);
			
			break;
		case R.id.tv_fenlei:
			viewpager.setCurrentItem(3);
			
			break;

		default:
			break;
		}
	}
	
	

}
