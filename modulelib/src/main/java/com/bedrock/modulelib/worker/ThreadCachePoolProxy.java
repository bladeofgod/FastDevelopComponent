package com.bedrock.modulelib.worker;

import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadCachePoolProxy {

    private ThreadPoolExecutor mExecutor;

    private void initThreadCachePoolExecutor(){
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
            synchronized (ThreadCachePoolProxy.class){
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
                    long keepAliveTime = 60L;
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    mExecutor = new ThreadPoolExecutor(
                            0,Integer.MAX_VALUE,keepAliveTime,unit,new SynchronousQueue<Runnable>()
                    );
                }
            }
        }
    }

    public void execute(Runnable task){
        initThreadCachePoolExecutor();
        mExecutor.execute(task);
    }
    public Future submit(Runnable task){
        initThreadCachePoolExecutor();
       return mExecutor.submit(task);
    }
    public void remove(Runnable task){
        initThreadCachePoolExecutor();
        mExecutor.remove(task);
    }

}
