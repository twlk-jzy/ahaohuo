package com.ahaohuo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static SharedPreferences sp;

    private static final String SP_NAME = "config";


    public static void init(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
    }

    public static void saveString(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public static String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public static void saveBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public static Boolean getBool(String key) {
        return sp.getBoolean(key, false);
    }

    public static void putBool(String key, boolean bool) {
        if (sp != null) {
            sp.edit().putBoolean(key, bool).apply();
        }
    }

    public static void putInt(String key, int value) {
        if (sp != null) {
            sp.edit().putInt(key, value).apply();
        }
    }

    public static int getInt(String key, int value) {
        if (sp != null) {
            sp.getInt(key, value);
        }
        return value;
    }


    public static void removeKey(String key) {
        if (sp != null) {
            sp.edit().remove(key).apply();
        }
    }

}
