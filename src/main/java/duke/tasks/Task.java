package duke.tasks;

public class Task {

    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String toDukeFileString() {
        String isDoneString = isDone ? "1" : "0";
        return isDoneString + "|" + name;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "x" : " ";
        return "[" + checkMark + "] " + name;
    }
}
