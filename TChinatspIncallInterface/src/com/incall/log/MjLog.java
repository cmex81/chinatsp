package com.incall.log;

import android.util.Log;

public class MjLog {
	
	private static final String PRE = "<mj> ";

	public static void e(String tag, String msg) {
		Log.e(tag, PRE + msg);
	}

	public static void w(String tag, String msg) {
		Log.w(tag, PRE + msg);
	}

	public static void d(String tag, String msg) {
		Log.d(tag, PRE + msg);
	}

	public static void i(String tag, String msg) {
		Log.i(tag, PRE + msg);
	}

	public static void v(String tag, String msg) {
		Log.v(tag, PRE + msg);
	}
}
