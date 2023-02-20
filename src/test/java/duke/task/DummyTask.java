package duke.task;

/**
 * Is a dummy class that extends from Task.
 * Used for testing other classes that expects a Task object.
 */
public class DummyTask extends Task {

    /**
     * Constructs a dummy Task instance.
     */
    public DummyTask() {
        super("");
    }
}
