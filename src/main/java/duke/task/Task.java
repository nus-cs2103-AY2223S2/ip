package duke.task;

public class Task {
    private boolean done;
    private String task;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return task;
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsNotDone() {
        done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + task;
    }

    public String toFileString() {
        return (done ? "1" : "0") + " | " + task;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            return task.equals(((Task) obj).task) && done == ((Task) obj).done;
        }
        return false;
    }

}
