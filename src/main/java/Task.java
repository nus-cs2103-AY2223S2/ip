package duke.task;

/**
 * Represents a Task which the user needs to complete.
 *
 * @author Karen
 */
public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Marks a Task as completed.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmarks a Task as uncompleted.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (done) {
            return "1" + " | " + this.name;
        } else {
            return "0" + " | " + this.name;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Task) {
            Task x = (Task) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }
}
