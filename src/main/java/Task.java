public class Task {
    private String nameOfTask;
    private int indexOfTask;
    private boolean done;
    private static int totalNumOfTask = 1;

    public Task(String name) {
        this.nameOfTask = name;
        this.indexOfTask = totalNumOfTask;
        totalNumOfTask++;
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

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.getNameOfTask();
    }
}
