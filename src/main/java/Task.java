import java.time.LocalDate;

public abstract class Task {
    private String nameOfTask;
    private boolean done;
    public static int totalNumOfTask = 0;

    public Task(String name) {
        this.nameOfTask = name;
        totalNumOfTask++;
        done = false;
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
    public static void decreaseNumOfTask() {
        totalNumOfTask--;
    }

    abstract public LocalDate getDate();

    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.getNameOfTask();
    }
}
