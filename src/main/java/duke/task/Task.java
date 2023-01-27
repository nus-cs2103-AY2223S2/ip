package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    protected static final String CATEGORY_KEY = "category";
    protected String description;
    protected boolean completed;

    public Task(String description, boolean completed) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a task cannot be empty");
        }
        this.description = description;
        this.completed = completed;
    }

    public abstract String serialize();

    public String getDescription() {
        return this.description;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean matches(String pattern) {
        return description.contains(pattern);
    }

    public String getStatusIcon() {
        return isCompleted() ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object instanceof Task) {
            Task t = (Task) object;
            return toString().equals(t.toString());
        } else {
            return false;
        }
    }
}
