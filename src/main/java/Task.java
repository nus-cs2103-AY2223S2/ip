public class Task {
    private static final String COMPLETED = "[O]";
    private static final String NOT_COMPLETED = "[ ]";
    private final String name;
    private boolean isCompleted;
    Task(String name) {
        this.name = name;
        isCompleted = false;
    }

    public void markTask() {
        isCompleted = true;
        System.out.println(DukeIO.wrapContent("Well done. Task has been marked as completed:\n" + stringWithStatus()));
    }

    public void unmarkTask() {
        isCompleted = false;
        System.out.println(DukeIO.wrapContent("Got it. Task has been unmarked:\n" + stringWithStatus()));
    }

    public String stringWithStatus() {
        return (isCompleted ? COMPLETED : NOT_COMPLETED) + " " + name;
    }
    @Override
    public String toString() {
        return name;
    }
}
