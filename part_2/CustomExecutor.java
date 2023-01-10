package com.company;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private int[] max = {-1, 0, 0, 0};
    private final int maxp = 10;

    public CustomExecutor()
    {
        super(Runtime.getRuntime().availableProcessors()/2, Runtime.getRuntime().availableProcessors()-1,
                300, TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<Runnable>());
    }

    public CustomExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public <T> Future<T> submit(Task<T> task) {
        max[task.getTaskType().getPriorityValue()]++;
        AdapterCall adapterCall = new AdapterCall<>(task , task.getTaskType().getPriorityValue());
        super.execute(adapterCall);
        return adapterCall;
    }

    public <T> Future<T> submit(Callable<T> task , TaskType taskType) {
        return submit(Task.createTask(task , taskType));
    }

    @Override
    public <T> Future<T> submit(Callable<T> task)
    {
        return submit(Task.createTask(task));
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        AdapterCall call = (AdapterCall) r;
        max[call.getP()]--;
    }

    public int getCurrentMax()
    {
        if(super.getQueue().isEmpty()){
            return maxp;
        }
        int max_p = 3;
        for (int i = 3; i >= 1; i--) {
            if(max[i] > 0)
                max_p = max[i];
        }
        return max_p;
    }

    public void gracefullyTerminate() {
        super.shutdown();
    }

    @Override
    public void setCorePoolSize(int corePoolSize) {
        super.setCorePoolSize(corePoolSize);
    }

    @Override
    public int getCorePoolSize() {
        return super.getCorePoolSize();
    }

    @Override
    public void setMaximumPoolSize(int maximumPoolSize) {
        super.setMaximumPoolSize(maximumPoolSize);
    }

    @Override
    public int getMaximumPoolSize() {
        return super.getMaximumPoolSize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }



}

