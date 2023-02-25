package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * constructs a task object.
     *
     * @param description describes the task.
     * @throws EmptyDescriptionException
     */
    public Task(String description) throws EmptyDescriptionException{
        if (description.equals("") || description.equals(" ")) {
            throw new EmptyDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }
    public abstract String getFileDescription();

    /**
     * obtains the icon for the status of the task.
     * @return String where an X demarcates a task that is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * sets a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * sets a task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}

