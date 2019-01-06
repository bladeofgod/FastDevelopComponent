package com.bedrock.modulelib.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {

    private static DialogUtil instance;

    public static DialogUtil getInstance(){
        if (null == instance){
            synchronized (DialogUtil.class){
                if (null == instance){
                    instance = new DialogUtil();
                }
            }
        }
        return instance;
    }

    public void showSingleMsg1Button(Context context,String title, String content, String btnInfo){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(content)
                .setPositiveButton(btnInfo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }

}
