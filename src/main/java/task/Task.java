package task;

import exception.MissingParameterException;

public abstract class Task {
    private boolean isDone;
    private final String description;

    public Task(String description) throws MissingParameterException {
        if (description == null || description.isBlank()) {
            throw new MissingParameterException("Missing description", "A task description is needed.");
        }

        this.isDone = false;
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
