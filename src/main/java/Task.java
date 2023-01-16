public class Task {
    private String nameOfTask;
    private int indexOfTask;
    private boolean done;
    private static int totalNumOfTask = 0;

    public Task(String name) {
        this.nameOfTask = name;
        totalNumOfTask++;
        this.indexOfTask = totalNumOfTask;
        done = false;
    }
    public int getIndexOfTask() {
        return indexOfTask;
    }
    public String getNameOfTask() {
        return nameOfTask;
    }
    public void taskDone() {
        this.done = true;
    }
    public void taskNotDone() {
        this.done = false;
    }
    public boolean isDone() {
        return this.done;
    }

    public static int getTotalNumOfTask() {
        return totalNumOfTask;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.getNameOfTask();
    }
}
