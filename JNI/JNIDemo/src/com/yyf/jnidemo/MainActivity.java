package com.yyf.jnidemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText etKey;
	private EditText etVal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etKey = (EditText)findViewById(R.id.etKey);
		etVal = (EditText)findViewById(R.id.etVal);

	}
	
	public void click(View view){
		String key = etKey.getText().toString();
		String val = etVal.getText().toString();
		
		if(val == null || val.equals("")){
			String valGet = PropertyUtils.getProperty(key, "nullyyf");
			Log.v("yyf", "valGet:"+valGet);
		}else {
			PropertyUtils.setProperty(key, val);
			
			String valGet2 = PropertyUtils.getProperty(key, "nullyyf2");
			Log.v("yyf", "valGet2:"+valGet2);
		}
	}

}
