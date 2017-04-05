package com.yyf.intentservice.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private final String TAG = "ISClient-MainActivity";
	
	private TextView tv;

	private int value;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tvResult);
	}
	
	public void send(View view){
		Intent it = new Intent();
		it.setClassName("com.yyf.intentservice.service", "com.yyf.intentservice.service.CusIntentService");
		it.putExtra("name", "yongfa.yang");
		it.putExtra("value", value);
		startService(it);
		Log.v(TAG, "IntentService action was sent.");
		tv.setText(String.valueOf(value++));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
