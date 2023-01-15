package task;

import exception.MissingParameterException;

public abstract class Task {
    private boolean isDone;
    private String description;

    public Task(String description) throws MissingParameterException {
        if (description == null || description.isBlank()) {
            throw new MissingParameterException("Missing description", "A task description is needed.");
        }

        this.isDone = false;
        this.description = description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s", this.isDone ? "X" : " ",
                this.description == null ? "[empty]" : this.description
        );
    }
}
