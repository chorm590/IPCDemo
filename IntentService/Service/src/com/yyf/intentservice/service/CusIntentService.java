package com.yyf.intentservice.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class CusIntentService extends IntentService {
	
	private final String TAG = "CusIntentService";
	
	private int timer;
	
	public CusIntentService(){
		this(Thread.currentThread().getStackTrace()[1].getClassName());
	}

	private CusIntentService(String name) {
		super(name);
		Log.v(TAG, "new CusIntentService("+name+")");
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.v(TAG, "onCreate");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.v(TAG, "onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.v(TAG, "onDestroy()");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.v(TAG, "onHandleIntent");
		int value = intent.getIntExtra("value", -1);
		String name = intent.getStringExtra("name");
		Log.v(TAG, "Result,name:"+name+",value:"+value);
		if(timer == 0){
			timer = 10;
			while(timer > 0){
				timer--;
				Log.v(TAG, "Timer:"+timer);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
