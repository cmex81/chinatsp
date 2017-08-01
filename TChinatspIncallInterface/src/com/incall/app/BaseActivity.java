package com.incall.app;

import android.app.Activity;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import java.security.InvalidParameterException;
import java.util.HashMap;

public class BaseActivity extends Activity{
	 private String theme = null;
	    private HashMap<String, Integer> themeMap = new HashMap();
	    private ContentObserver mThemeChangedContentObserver = new ContentObserver(new Handler()) {
	        public void onChange(boolean selfChange) {
	            String newTheme = BaseActivity.this.getNewThemeName();
	            Log.d(this.getClass().getSimpleName(), "<mj> mThemeChangedContentObserver : selfChange=" + selfChange + ", new theme=" + newTheme);
	            if(!TextUtils.isEmpty(newTheme) && !TextUtils.equals(newTheme, BaseActivity.this.theme)) {
	                BaseActivity.this.setTheme(((Integer)BaseActivity.this.themeMap.get(newTheme)).intValue());
	                BaseActivity.this.onThemeChanged(((Integer)BaseActivity.this.themeMap.get(newTheme)).intValue());
	            }

	            BaseActivity.this.theme = newTheme;
	        }
	    };

	    public BaseActivity() {
	    }

	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        Log.d(this.getClass().getSimpleName(), "onCreate");
	        this.requestWindowFeature(1);
	        if(this.getThemeIds() != null && this.getThemeNames() != null) {
	            if(this.getThemeIds().length != 0 && this.getThemeNames().length != 0) {
	                if(this.getThemeIds().length != this.getThemeNames().length) {
	                    throw new InvalidParameterException("getThemeIds().length != getThemeNames().length");
	                } else {
	                    for(int i = 0; i < this.getThemeNames().length; ++i) {
	                        this.themeMap.put(this.getThemeNames()[i], Integer.valueOf(this.getThemeIds()[i]));
	                    }
	                    this.theme = this.getNewThemeName();
	                    this.setTheme(((Integer)this.themeMap.get(this.theme)).intValue());
	                    Log.d(this.getClass().getSimpleName(), "Theme = " + this.theme);
	                    this.getContentResolver().registerContentObserver(System.getUriFor("coagent_theme"), false, this.mThemeChangedContentObserver);
	                }
	            } else {
	                throw new InvalidParameterException("0 == getThemeIds().length || 0 == getThemeNames().length");
	            }
	        } else {
	            throw new NullPointerException("There is NOT one valid Theme,\nPlease override getThemeIds() & getThemeNames() in your Activity");
	        }
	    }

	    protected void onStart() {
	        super.onStart();
	        Log.d(this.getClass().getSimpleName(), "onStart");
	    }

	    protected void onResume() {
	        super.onResume();
	        String newTheme = this.getNewThemeName();
	        Log.d(this.getClass().getSimpleName(), "onResume theme: " + this.theme + " -> " + newTheme);
	        if(!TextUtils.isEmpty(newTheme) && !TextUtils.equals(newTheme, this.theme)) {
	            this.onThemeChanged(((Integer)this.themeMap.get(newTheme)).intValue());
	        }
	    }

	    protected void onRestart() {
	        super.onRestart();
	        Log.d(this.getClass().getSimpleName(), "onRestart");
	    }

	    protected void onPause() {
	        super.onPause();
	        Log.d(this.getClass().getSimpleName(), "onPause");
	    }

	    protected void onStop() {
	        super.onStop();
	        Log.d(this.getClass().getSimpleName(), "onStop");
	    }

	    protected void onDestroy() {
	        super.onDestroy();
	        Log.d(this.getClass().getSimpleName(), "onDestroy");
	        this.getContentResolver().unregisterContentObserver(this.mThemeChangedContentObserver);
	    }

	    protected int[] getThemeIds() {
	        return null;
	    }

	    protected String[] getThemeNames() {
	        return null;
	    }

	    protected void onThemeChanged(int themeId) {
	        Log.i(this.getClass().getSimpleName(), "Theme is changed by other!");
	    }

	    public int getThemeResource() {
	        return ((Integer)this.themeMap.get(this.theme)).intValue();
	    }

	    private String getNewThemeName() {
	        String newTheme = System.getString(this.getContentResolver(), "coagent_theme");
	        if(TextUtils.isEmpty(newTheme)) {
	            newTheme = this.getThemeNames()[0];
	        } else if(this.themeMap.get(newTheme) == null) {
	            Log.e(this.getClass().getSimpleName(), "[" + newTheme + "] is a Invalid Theme," + " set to default [" + this.getThemeNames()[0] + "]");
	            newTheme = this.getThemeNames()[0];
	        }

	        return newTheme;
	    }
	

}
