public class Task {
    protected String description;
    protected boolean isDone;
    protected static int counter = 0;
    protected int taskNum;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        counter++;
        taskNum = counter;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markStatus(boolean status) {
        this.isDone = status;
    }

//    public int getTaskNum() {
//        return this.taskNum;
//    }

//    public int getNumberOfTasks() {
//        return this.counter;
//    }

    @Override
    public String toString() {
        return this.taskNum + ".[" + this.getStatusIcon() + "] " + this.description;
    }

    //...
}
