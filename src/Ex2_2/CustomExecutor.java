package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {
    private int[] max = {-1, 0, 0, 0};
    private final int maxp = 10;

    public CustomExecutor()
    {
        super(Runtime.getRuntime().availableProcessors()/2,
                Runtime.getRuntime().availableProcessors()-1,
                300,
                TimeUnit.MILLISECONDS,
                new PriorityBlockingQueue<Runnable>());
    }


    public <T> Future<T> submit(Task<T> task) throws IllegalArgumentException {
        if (task != null) {
            max[task.getTaskType().getPriorityValue()]++;

            AdapterCall adapterCall = new AdapterCall<>(task, task.getTaskType().getPriorityValue());
            super.execute(adapterCall);
            return adapterCall;
        }
        throw new IllegalArgumentException();
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
        if(r != null)  max[((AdapterCall) r).getP()]--;
    }

    public int getCurrentMax()
    {
        boolean ok = false;
        int max_p = 3;
        for (int i = 3; i >= 1; i--) {
            if(max[i] > 0)
            {
                max_p = max[i];
                ok = true;
            }
        }
        if (ok) return max_p;
        return maxp;

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
        return "Queue: " + super.getQueue().toString() + "max priority in queue: " + getCurrentMax();
    }



}

