package chattime.task;

import java.time.LocalDate;

public class Task {

    private static int tasksCount = 0;

    private String description;
    private boolean isDone;

    public Task(String content) {
        description = content;
        isDone = false;
        tasksCount++;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public static int getCount() {
        return tasksCount;
    }

    public void removeTask() {
        tasksCount--;
    }

    public static String printTotalTask() {
        return "Now you have " + tasksCount + " tasks in the list.";
    }

    public String toDataString() {
        return String.format(" @ %d @ %s", isDone ? 1 : 0, description);
    }

    public boolean isOnDate(LocalDate time) {
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

}
