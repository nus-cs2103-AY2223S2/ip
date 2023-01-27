import java.time.format.DateTimeFormatter;
abstract class Task {
    String taskDescription;
    Boolean isDone = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

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
