package com.company;
import java.util.Objects;
import java.util.concurrent.Callable;

public class Task<T> implements Callable<T> , Comparable<Task<T>> {
    TaskType taskType;
    Callable <T> task;

    //constructors
    private Task(Callable task) {
        this.task = task;
        this.taskType.setPriority(3);
    }

    private Task(Callable task , TaskType taskType) {
        this.task = task;
        this.taskType = taskType;
    }

    //Create task
    public static Task createTask(Callable callable, TaskType taskType){
        return new Task(callable , taskType);
    }

    public static Task createTask(Callable callable) {
        return new Task(callable);
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public Callable<T> getTask() {
        return task;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public void setTask(Callable<T> task) {
        this.task = task;
    }


    @Override
    public T call() throws Exception {

        try
        {
            return this.task.call();
        }
        catch (Exception e)
        {
                throw new Exception();
        }
    }

    /**
     *
     * @param otherTask the object to be compared.
     * @return 1 / -1 / 0 depends on if this taskType is larger smaller or even
     *          to the object to be compared.
     */
    @Override
    public int compareTo(Task<T> otherTask) {
        return  Integer.compare(this.taskType.getPriorityValue() , otherTask.taskType.getPriorityValue());
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Task)) return false;
        Task<T> other = (Task)o;
        if(this.task.equals(other.task) && this.taskType.getPriorityValue() == other.taskType.getPriorityValue())
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task, taskType);
    }

    @Override
    public String toString() {
        return "{" + this.task.toString() +
                "Priority: " + taskType.getPriorityValue()
                + "}";
    }
}
