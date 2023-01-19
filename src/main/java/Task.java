public abstract class Task {
    private static final String COMPLETED = "[O]";
    private static final String NOT_COMPLETED = "[ ]";
    private final String name;
    private boolean isCompleted;
    Task(String name) throws DukeException {
        if (name.isEmpty()) {
            throw new DukeException("Empty description for task");
        }
        this.name = name;
        isCompleted = false;
    }

    public void markTask() {
        isCompleted = true;
        System.out.println(DukeIO.wrapContent("Well done. Task has been marked as completed:\n\t\t" + this));
    }

    public void unmarkTask() {
        isCompleted = false;
        System.out.println(DukeIO.wrapContent("Got it. Task has been unmarked:\n\t\t" + this));
    }
    @Override
    public String toString() {
        return (isCompleted ? COMPLETED : NOT_COMPLETED) + " " + name;
    }
}
