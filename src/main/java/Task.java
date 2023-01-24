public class Task {

    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public String toDukeFileString() {
        String isDoneString = this.isDone ? "1" : "0";
        return isDoneString + "|" + this.name;
    }

    @Override
    public String toString() {
        String checkMark = isDone ? "x" : " ";
        return "[" + checkMark + "] " + this.name;
    }
}
