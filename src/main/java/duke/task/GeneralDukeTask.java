package duke.task;

/**
 * An abstract Task class encapsulating a task in Duke, which can be extended
 * by more specific tasks like Events, toDos, etc.
 */

public abstract class GeneralDukeTask {
    private String information;
    boolean isDone;

    public GeneralDukeTask(String info) {
        this.information = info;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.information;
        } else {
            return "[ ] " + this.information;
        }
    }
}
