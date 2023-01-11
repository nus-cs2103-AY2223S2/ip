package duke.task;

/**
 * An abstract Task class encapsulating a task in Duke, which can be extended
 * by more specific tasks like Events, toDos, etc.
 */

public abstract class GeneralDukeTask {
    private String information;
    private boolean isDone;

    public GeneralDukeTask(String info) {
        this.information = info;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }
}
