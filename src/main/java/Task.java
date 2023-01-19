public class Task {
    boolean done;
    String task;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsNotDone() {
        done = false;
    }

    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + task;
    }
}
