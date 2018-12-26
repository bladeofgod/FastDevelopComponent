package com.bedrock.modulelib;

import android.app.Activity;

import java.util.Stack;

/*
* 管理activity 可结合notification 返回最近的一个activity
*
* */

public class ActivityManager {

    private static ActivityManager instance;
    private static Stack<Activity> activityStack;

    public static ActivityManager getSingleton(){
        if (instance == null){
            synchronized (ActivityManager.class){
                if (instance == null){
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        if (activityStack.contains(activity)){
            activityStack.remove(activity);
        }
        activityStack.add(activity);
    }

    public Activity getCurrentActivity(){
        if (null != activityStack && null != activityStack.lastElement()){
            return activityStack.lastElement();
        }
        return null;
    }

    public void finishActivity(Activity activity){
        if (activity != null){
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls){
        Stack<Activity> activities = new Stack<>();
        for (Activity activity : activityStack){
            if (activity.getClass().equals(cls)){
                activities.add(activity);
            }
        }

        for (Activity activity:activities){
            finishActivity(activity);
        }
    }

    public void finishAllActivity(){
        if (activityStack == null){
            return;
        }
        for (int i=0 ; i<activityStack.size();i++){
            if (null != activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }



}

















