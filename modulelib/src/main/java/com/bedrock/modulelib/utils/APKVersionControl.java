package com.bedrock.modulelib.utils;

import android.content.Context;
import android.content.pm.PackageManager;

public class APKVersionControl {

    public static float getVersionCode(Context context){
        float versionCode = 0.0f;
        try {
            versionCode = context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
