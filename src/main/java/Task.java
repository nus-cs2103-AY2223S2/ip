abstract class Task {
    String taskDescription;
    Boolean isDone = false;

    void mark() {
        isDone = true;
    }

    void unmark() {
        isDone = false;
    }

    String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", isDone ? 'X' : ' ', getTaskDescription());
    }
}
