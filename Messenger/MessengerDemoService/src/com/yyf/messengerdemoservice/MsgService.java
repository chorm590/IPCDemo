package com.yyf.messengerdemoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MsgService extends Service {
	
	private final String TAG = "MsgService";
	
	private Message msgFromClient;

	@Override
	public IBinder onBind(Intent intent) {
		// 客户端只能从服务器端获得Messenger，这样的Messenger才是被服务器认可的。
		return srvMsger.getBinder();
	}
	
	private void getTime(){
		Message msgReply = Message.obtain();
//		msgReply.obj = "get time,fuck you!";
		Bundle bundle = new Bundle();
		bundle.putString("result", "get time,fuck you!!");
		msgReply.setData(bundle);
		try {
			msgFromClient.replyTo.send(msgReply);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	private void getDate(){
		Message msgReply = Message.obtain();
//		msgReply.obj = "get date,damn you!";
		/*
		 * Important!!!
		 * 在跨进程的时候不能使用msg.obj的方式来传递对象。
		 * 而应该使用Bundle。
		 * */
		Bundle bundle = new Bundle();
		bundle.putString("result", "get date,damn you!");
		msgReply.setData(bundle);
		try {
			msgFromClient.replyTo.send(msgReply);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private Messenger srvMsger = new Messenger(new Handler(){
		
		public void handleMessage(Message msgFromClient) {
			MsgService.this.msgFromClient = msgFromClient;
			switch(msgFromClient.what){
				case Contants.GET_TIME:{
					getTime();
				}break;
				case Contants.GET_DATE:{
					getDate();
				}break;
				default:{
					Log.v(TAG, "The command from client is incorrect. command: "+msgFromClient.what);
				}break;
			}//switch  --  end.
		};
		
	});
	
}
