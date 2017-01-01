package com.yyf.messengerdemoclient;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MsgClient extends Activity {
	
	private final String TAG = "MsgClient";
	
	private final String result = "结果：";
	
	private Button btnConn;
	private TextView tvResult;
	private EditText etInput;
	
	private Messenger msgerFromSrv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// 先初始化一波。
		btnConn = (Button) findViewById(R.id.btnConn);
		tvResult = (TextView) findViewById(R.id.tvResult);
		etInput = (EditText) findViewById(R.id.etInput);
		
		btnConn.setText("连接中...");
		btnConn.setEnabled(false);
		btnConn.setOnClickListener(new ConnBtnClickListener());
		
		tvResult.setText(result);
		
		// 尝试连接服务器。
		mHandler.sendEmptyMessageDelayed(0, 3000);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unbindService(srvConn);
	}
	
	private ServiceConnection srvConn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.v(TAG, "onServiceDisconnected.");
//			btnConn.setText("连接不到服务器");
			// 此处应有重连操作，后续再实现。
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.v(TAG, "onServiceConnected.");
			msgerFromSrv = new Messenger(service);
			btnConn.setText("连接成功！");
			btnConn.postDelayed(new Runnable() {
				
				@Override
				public void run() {
					btnConn.setText("获取结果");
					btnConn.setEnabled(true);
				}
			}, 1000);
		}
	};
	
	private class ConnBtnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			int command;
			try{
				command = Integer.valueOf(etInput.getText().toString());
			}catch(NumberFormatException e){
				command = -1;
			}
			Log.v(TAG, "getting msg from service,the command is: "+command);
			Message msg = Message.obtain();
			msg.what = command;
			msg.replyTo = msgerFromClient;
			try {
				msgerFromSrv.send(msg);
				mHandler.sendEmptyMessageDelayed(9, 3000);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 用来模拟连接服务器的延时。
	 * */
	private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			if(msg.what == 9){
				Message msg2 = Message.obtain();
				msg2.what = 101;
				msg2.replyTo = msgerFromClient;
				try {
					msgerFromSrv.send(msg2);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}else{
				Intent srvIntent = new Intent();
				srvIntent.setClassName("com.yyf.messengerdemoservice", "com.yyf.messengerdemoservice.MsgService");
				bindService(srvIntent, srvConn, Service.BIND_AUTO_CREATE);
			}
			
		};
		
	};
	
	private Messenger msgerFromClient = new Messenger(new Handler(){
		
		public void handleMessage(Message msgFromService) {
//			tvResult.setText(msgFromService.obj.toString());
			String result = msgFromService.getData().getString("result");
			if(result == null)
				result = "null";
			tvResult.setText(result);
		};
		
	});

}
