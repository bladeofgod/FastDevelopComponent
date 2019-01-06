package com.bedrock.modulelib.utils;

import android.util.Log;

public class Logger {
    public static void logMessage(String title,String info){
        Log.i("debuginfo  :" + title,"________" + info);
    }
}
