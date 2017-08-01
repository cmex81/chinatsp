package com.incall.proxy.date;


import android.util.Log;
import com.incall.proxy.date.LunarUtils;
import java.util.Calendar;
import java.util.Locale;

public class DateUtil {
    private static final String TAG = DateUtil.class.getSimpleName();

    public DateUtil() {
    }

    public static String getLunar(boolean isOnlyGetDate) {
        Calendar today = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        int year = today.get(1);
        int month = today.get(2) + 1;
        int day = today.get(5);
        return getLunar(year, month, day, isOnlyGetDate);
    }

    public static String getLunar(int year, int month, int day, boolean isOnlyGetDate) {
        Log.d(TAG, "year is " + year + " month is " + month + " day is " + day);
        Calendar calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);
        calendar.set(year, month - 1, day);
        LunarUtils lunarUtils = new LunarUtils(calendar);
        StringBuffer result = new StringBuffer();
        if(!isOnlyGetDate) {
            result.append(lunarUtils.cyclical());
            result.append(lunarUtils.animalsYear());
            result.append("年");
        }

        result.append(lunarUtils.toString());
        String lunar = result.toString();
        Log.d(TAG, "getLunar " + lunar);
        return lunar;
    }
}