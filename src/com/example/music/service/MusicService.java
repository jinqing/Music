package com.example.music.service;

import java.io.IOException;
import java.util.List;

import com.example.music.bean.Mp3;
import com.example.music.util.MusicUtil;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MusicService extends Service{
	private Context context;
	private Handler handler;
	private MediaPlayer  mMediaPlayer; 
	private int currentTime = 0;//歌曲播放进度
	private int currentListItme = -1;//当前播放第几首歌
	private List<Mp3> songs;//要播放的歌曲集合
	private String url;//当前播放路径
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mMediaPlayer=new MediaPlayer();
		songs=MusicUtil.getAllSongs(getApplicationContext());
		Log.i("绑定服务启动onCreate", "绑定服务启动onCreate");
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		url=intent.getStringExtra("music_url");
		if(url!=null) playMusic(url);
		getitem();
//		new Thread(new Runnable() {
//			public void run() {
//				while (mMediaPlayer.isPlaying()) {
//					Message message=new Message();
//					message.arg1=getCurrent();
//					handler.sendMessage(message);
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		mMediaPlayer=new MediaPlayer();
		Log.i("绑定服务启动onBind", "绑定服务启动onBind");
		return null;
	}

	/**
	 *	自定义绑定Service类，通过这里的getService得到Service，之后就可调用Service这里的方法了
	 */
	public class LocalBinder extends Binder {
		public MusicService getService() {
			return MusicService.this;
		}
	}
	
	public void setSongs(List<Mp3> songs) {
		this.songs = songs;
	}
	
	/**
	 *得到当前播放进度 
	 */
	public int getCurrent() {
		if (mMediaPlayer.isPlaying()) {
			return mMediaPlayer.getCurrentPosition();
		} else {
			return currentTime;
		}
	}
	
	/**
	 *	跳到输入的进度 
	 */
	public void movePlay(int progress) {
		mMediaPlayer.seekTo(progress);
		currentTime = progress;
	}
	
	/**
	 *	根据歌曲存储路径播放歌曲 
	 */
	public void playMusic(String path) {
		try {
			/* 重置MediaPlayer */
			mMediaPlayer.reset();
			/* 设置要播放的文件的路径 */

			mMediaPlayer.setDataSource(path);
			// mMediaPlayer = MediaPlayer.create(this,
			// R.drawable.bbb);播放资源文件中的歌曲
			/* 准备播放 */
			mMediaPlayer.prepare();
			/* 开始播放 */
			mMediaPlayer.start();

			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer arg0) {
					// 播放完成一首之后进行下一首
					nextMusic();
				}
			});
		} catch (IOException e) {
		}
	}
	
	/* 下一首 */
	public void nextMusic() {
		if (++currentListItme >= songs.size()) {
			currentListItme = 0;
		}
		playMusic(songs.get(currentListItme).getUrl());
	}

	/* 上一首 */
	public void frontMusic() {
		Log.v("itme", currentListItme + "hree");
		if (--currentListItme < 0) {
			currentListItme = songs.size() - 1;
		}
		playMusic(songs.get(currentListItme).getUrl());
	}
	
	/**
	 *	歌曲是否真在播放 
	 */
	public boolean isPlay() {
		return mMediaPlayer.isPlaying();
	}
	
	/**
	 *	暂停或开始播放歌曲 
	 */
	public void pausePlay() {
		if (mMediaPlayer.isPlaying()) {
			currentTime = mMediaPlayer.getCurrentPosition();
			mMediaPlayer.pause();
		} else {
			mMediaPlayer.start();
		}
	}
	
	/**
	 * 获取歌曲的总时间
	 */
	public int getDuration() {
		return mMediaPlayer.getDuration();
	}
	
	/**
	 * 当前播放第几首
	 */
	public void getitem(){
		for(int i=0;i<songs.size();i++){
			if(songs.get(i).getUrl().equals(url)){
				currentListItme=i;
			}
		}
	}
	
	public void onDestroy() {

		super.onDestroy();
		mMediaPlayer.release();
		
	}

}
