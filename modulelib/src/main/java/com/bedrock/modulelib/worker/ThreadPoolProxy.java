package com.bedrock.modulelib.worker;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolProxy {

    ThreadPoolExecutor mExecutor;
    private int mCorePoolSize;
    private int mMaximumPoolSize;

    public ThreadPoolProxy(int corePoolSize,int maximumPoolSize){
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
    }


    private void initThreadPoolExecutor(){
        if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
            synchronized (ThreadPoolProxy.class){
                if (mExecutor == null || mExecutor.isShutdown() || mExecutor.isTerminated()){
                    long keepAliveTime = 3000;
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue workQueue = new LinkedBlockingQueue();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();
                    mExecutor = new ThreadPoolExecutor(mCorePoolSize,mMaximumPoolSize,
                            keepAliveTime,unit,workQueue,threadFactory,handler);
                }
            }
        }
    }

    public void execute(Runnable task){
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }
    public Future submit(Runnable task){
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }
    public void remove(Runnable task){
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }

}
