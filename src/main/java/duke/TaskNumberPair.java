package duke;

public class TaskNumberPair {
    private Task task;
    private int number;

    public TaskNumberPair(Task task, int number) {
        this.task = task;
        this.number = number;
    }

    public Task getTask() {
        return this.task;
    }

    public int getNumber() {
        return this.number;
    }
}
