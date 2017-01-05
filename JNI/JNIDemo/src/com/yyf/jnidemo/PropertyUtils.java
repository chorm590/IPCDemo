package com.yyf.jnidemo;

public class PropertyUtils {

	
	public static native String getProperty(String key, String defVal);
	
	public static native void setProperty(String key, String val);
	
	static{
		System.loadLibrary("jnidemoojni");
	}
}
