package com.bedrock.modulelib.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class JumpToMarket {
    public static void gotoAppMarket(Context context,String packageName){
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent gotoMarket = new Intent(Intent.ACTION_VIEW,uri);
        try {
            context.startActivity(gotoMarket);
        }catch (ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}
