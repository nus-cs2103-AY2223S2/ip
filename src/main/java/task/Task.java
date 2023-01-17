package task;


/** A task that the user has to do. */
public class Task {
    private final String name;
    private final boolean isDone;


    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public Task mark(boolean isDone) {
        return new Task(this.name, isDone);
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("%s %s",
            getStatusString(),
            getName()
        );
    }

    private String getStatusString() {
        if (isDone) {
            return "[X]";
        }
        return "[ ]";
    }
}
