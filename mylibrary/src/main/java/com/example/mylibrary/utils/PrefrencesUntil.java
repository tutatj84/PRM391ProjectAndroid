package com.example.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mylibrary.MyAppLib;

public class PrefrencesUntil {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public PrefrencesUntil(){

    }
    private static void init(){
        if (sharedPreferences == null) {
            sharedPreferences = MyAppLib.context.getSharedPreferences("com.toshiro97.mpay", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public static void saveString(String key, String val){
        init();
        editor.putString(key,val);
        editor.commit();
    }

    public static String getString(String key, String def){
        init();
        return sharedPreferences.getString(key,def);
    }

    public static String getString(String key){
        init();
        return sharedPreferences.getString(key,"");
    }

    public static void saveBol(String key, boolean b){
        init();
        editor.putBoolean(key,b);
        editor.commit();
    }

    public static boolean getBol(String key){
        init();
        return sharedPreferences.getBoolean(key,false);
    }

    public static void deleteAll(){
        init();
        editor.clear();
        editor.commit();
    }
}
