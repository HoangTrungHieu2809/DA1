package com.example.da1;

import android.content.Context;
import android.content.SharedPreferences;

public class AttendanceManager {
    private static final String PREF_NAME = "attendance_prefs";
    private static final String KEY_LAST_ATTENDANCE_DATE = "last_attendance_date";

    public static void saveAttendanceDate(Context context, String date) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_LAST_ATTENDANCE_DATE, date);
        editor.apply();
    }

    public static String getLastAttendanceDate(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_LAST_ATTENDANCE_DATE, "");
    }
}
