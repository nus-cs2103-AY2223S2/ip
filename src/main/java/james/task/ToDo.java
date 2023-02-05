package james.task;

import james.exception.JamesException;

/**
 * The todo class.
 * A todo is a task that is listed on the taskList without a deadline.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    @Override
    public String toStoreString() {
        return "T | " + super.toStoreString() + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}

