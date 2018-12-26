package com.bedrock.modulelib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static BaseApplication singleton = null;
    public static BaseApplication getSingleton(){
        if (singleton == null){
            syncInit();
        }
        return singleton;
    }
    private static synchronized void syncInit(){
        if (null == singleton){
            singleton = new BaseApplication();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        registerActivityLifecycleCallbacks(this);
    }


    private int activityCount=0;
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCount++;

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
