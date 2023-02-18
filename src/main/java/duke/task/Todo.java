package duke.task;

/**
 * Class to represent Todotask created by user
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    /**
     * @return String version of todotask
     */
    @Override
    public String toString() {
        if (super.getIsCompleted()) {
            return String.format("[T][X] %s\n", super.getTaskName());
        } else {
            return String.format("[T][ ] %s\n", super.getTaskName());
        }
    }
}
