package chattime.task;

import java.time.LocalDate;

public class Task {

    private String description;
    private boolean isDone;
    private static int count = 0;

    public Task() {}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;
    }

    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public static int getCount() {
        return count;
    }

    public void removeTask() {
        count--;
    }

    public static String totalTask() {
        return "Now you have " + count + " tasks in the list.";
    }

    public String toDataString() {
        return String.format(" @ %d @ %s", this.isDone ? 1 : 0, this.description);
    }

    public boolean onDate(LocalDate time) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
