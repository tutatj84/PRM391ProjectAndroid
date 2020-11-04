package com.example.mylibrary2.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;

import com.example.mylibrary2.MyAppLib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AboutPhone {
    public static String getDevice(){
       String deviceID = Secure.getString(MyAppLib.context.getContentResolver(),
                Secure.ANDROID_ID);
       return deviceID;
    }
    public static String mD5Convert(String str) {
        String result = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest.digest());
            result = bigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static boolean checkOnlineState() {
        ConnectivityManager CManager =
                (ConnectivityManager) MyAppLib.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo NInfo = CManager.getActiveNetworkInfo();
        return NInfo != null && NInfo.isConnectedOrConnecting();
    }
}
