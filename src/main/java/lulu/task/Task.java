package lulu.task;

import lulu.exception.LuluException;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean containsDescription(String s) {
        return this.description.contains(s);
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public String toMemory() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public abstract void update(String updateInformation);
}
