//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.incall.app;

import android.app.Dialog;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.android.internal.app.AlertController;
import com.android.internal.app.AlertController.AlertParams;
import java.util.LinkedHashMap;

public class CoagentDialog extends Dialog {
    private AlertController mAlert;
    private String mTheme = null;
    private LinkedHashMap<String, Integer> mThemeMap = new LinkedHashMap();
    private ContentObserver mThemeChangedContentObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean selfChange) {
            String newTheme = CoagentDialog.this.getNewThemeName();
            Log.d(this.getClass().getSimpleName(), "<mj> mThemeChangedContentObserver : selfChange=" + selfChange + ", new theme=" + newTheme);
            if(!TextUtils.isEmpty(newTheme) && !TextUtils.equals(newTheme, CoagentDialog.this.mTheme)) {
                CoagentDialog.this.setTheme(((Integer)CoagentDialog.this.mThemeMap.get(newTheme)).intValue());
                if(CoagentDialog.this.mOnThemeChangedListener != null) {
                    CoagentDialog.this.mOnThemeChangedListener.onThemeChanged(((Integer)CoagentDialog.this.mThemeMap.get(newTheme)).intValue());
                }
            }

            CoagentDialog.this.mTheme = newTheme;
        }
    };
    private CoagentDialog.OnThemeChangedListener mOnThemeChangedListener;

    protected CoagentDialog(Context context, LinkedHashMap<String, Integer> themeMap, int theme, boolean createThemeContextWrapper) {
        super(context, theme);
        if(themeMap != null && themeMap.size() != 0) {
            this.mThemeMap = themeMap;
            this.mTheme = this.getNewThemeName();
            if(theme != ((Integer)this.mThemeMap.get(this.mTheme)).intValue()) {
                this.setTheme(((Integer)this.mThemeMap.get(this.mTheme)).intValue());
            }

            this.getWindow().alwaysReadCloseOnTouchAttr();
            this.mAlert = new AlertController(this.getContext(), this, this.getWindow());
        } else {
            throw new NullPointerException("There is NOT one valid Theme !");
        }
    }

    public void setTheme(int resid) {
        this.getContext().setTheme(resid);
    }

    protected void onStart() {
        Log.i(this.getClass().getSimpleName(), "onStart()");
        super.onStart();
    }

    public void onAttachedToWindow() {
        Log.i(this.getClass().getSimpleName(), "onAttachedToWindow()");
        this.getContext().getContentResolver().registerContentObserver(System.getUriFor("coagent_theme"), false, this.mThemeChangedContentObserver);
    }

    public void onDetachedFromWindow() {
        Log.i(this.getClass().getSimpleName(), "onDetachedFromWindow()");
        this.getContext().getContentResolver().unregisterContentObserver(this.mThemeChangedContentObserver);
    }

    protected void onStop() {
        Log.i(this.getClass().getSimpleName(), "onStop()");
        super.onStop();
    }

    private String getNewThemeName() {
        String newTheme = System.getString(this.getContext().getContentResolver(), "coagent_theme");
        String defTheme = (String)this.mThemeMap.keySet().toArray()[0];
        if(TextUtils.isEmpty(newTheme)) {
            newTheme = defTheme;
        } else if(this.mThemeMap.get(newTheme) == null) {
            Log.e(this.getClass().getSimpleName(), "[" + newTheme + "] is a Invalid Theme," + " set to default [" + defTheme + "]");
            newTheme = defTheme;
        }

        return newTheme;
    }

    public void setOnThemeChangedListener(CoagentDialog.OnThemeChangedListener themeChangedListener) {
        this.mOnThemeChangedListener = themeChangedListener;
    }

    static int resolveDialogTheme(Context context, int resid) {
        if(resid == 1) {
            return 16974590;
        } else if(resid == 2) {
            return 16974606;
        } else if(resid == 3) {
            return 16974610;
        } else if(resid == 4) {
            return 16974618;
        } else if(resid == 5) {
            return 16974619;
        } else if(resid >= 16777216) {
            return resid;
        } else {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(16843529, outValue, true);
            return outValue.resourceId;
        }
    }

    public static class Builder {
        private final AlertParams P;
        private int mTheme;
        private LinkedHashMap<String, Integer> mThemeMap;

        public Builder(Context context, LinkedHashMap<String, Integer> themeMap) {
            if(themeMap != null && themeMap.size() != 0) {
                String newTheme = System.getString(context.getContentResolver(), "coagent_theme");
                String defTheme = (String)themeMap.keySet().toArray()[0];
                if(TextUtils.isEmpty(newTheme)) {
                    newTheme = defTheme;
                } else if(themeMap.get(newTheme) == null) {
                    Log.e(this.getClass().getSimpleName(), "[" + newTheme + "] is a Invalid Theme," + " set to default [" + defTheme + "]");
                    newTheme = defTheme;
                }

                this.mThemeMap = themeMap;
                this.mTheme = ((Integer)themeMap.get(newTheme)).intValue();
                this.P = new AlertParams(new ContextThemeWrapper(context, CoagentDialog.resolveDialogTheme(context, this.mTheme)));
            } else {
                throw new NullPointerException("There is NOT one valid Theme !");
            }
        }

        public CoagentDialog create() {
            CoagentDialog dialog = new CoagentDialog(this.P.mContext, this.mThemeMap, this.mTheme, false);
            this.P.apply(dialog.mAlert);
            dialog.setCancelable(this.P.mCancelable);
            if(this.P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }

            dialog.setOnCancelListener(this.P.mOnCancelListener);
            dialog.setOnDismissListener(this.P.mOnDismissListener);
            if(this.P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.P.mOnKeyListener);
            }

            return dialog;
        }
    }

    public interface OnThemeChangedListener {
        void onThemeChanged(int var1);
    }
}
