package com.company;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class CustomExecutor implements ExecutorService {

    int cores = Runtime.getRuntime().availableProcessors() ;
    ThreadPoolExecutor threadPool = new ThreadPoolExecutor
            (cores/2 , cores-1,
            300, TimeUnit.MILLISECONDS,
             new PriorityBlockingQueue<Runnable>());
    int[] maxP = new int[4];

    @Override
    public void execute(Runnable command) {
        Task t = (Task) command;
        maxP[t.taskType.getPriorityValue()]++;
        threadPool.execute((Runnable) t);
    }

    @Override
    public void shutdown() {
        threadPool.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return threadPool.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return threadPool.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return threadPool.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return threadPool.awaitTermination(timeout , unit);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        Task t = (Task) task;
        maxP[((Task<T>) task).taskType.getPriorityValue()]++;
        return threadPool.submit(task);
    }

    public <T> Future<T> submit(Callable task , TaskType taskType) {
        Task t = Task.createTask(task);
        t.setTaskType(taskType);
        maxP[((Task) task).taskType.getPriorityValue()]++;
        return threadPool.submit(task);
    }

    public <T> Future<T> submit(Callable task, T result) {
        Task t = Task.createTask(task);
        maxP[((Task) task).taskType.getPriorityValue()]++;
        return threadPool.submit((Runnable)task , result);
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        Task t = Task.createTask((Callable) task);
        maxP[((Task<T>) task).taskType.getPriorityValue()]++;
        return threadPool.submit(task , result);
    }

    @Override
    public Future<?> submit(Runnable task) {
        Task t = Task.createTask((Callable) task);
        maxP[((Task) task).taskType.getPriorityValue()]++;
        return threadPool.submit(task);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return threadPool.invokeAll(tasks);
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return threadPool.invokeAll(tasks , timeout , unit);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return threadPool.invokeAny(tasks);
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return threadPool.invokeAny(tasks , timeout , unit);
    }

    //@Override
    public void close() {
        //this.threadPool.close();
    }


//    @Override
//    private  void AfterExe(Runnable exe){
//        Task t = (Task) exe;
//        maxP[t.getTaskType().getPriorityValue()]--;
//    }

    public int getCurrentMax() {
        int max = -1;
        for (int i = 1; i < maxP.length; i++) {
            if(maxP[i] > max) max = maxP[i];
        }
        return max;
    }

    public void gracefullyTerminate() {
        threadPool.isShutdown();
    }
}
