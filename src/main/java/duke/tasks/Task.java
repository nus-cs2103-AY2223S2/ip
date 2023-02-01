package duke.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable{
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }



    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    abstract String getTypeIcon();

    @Override
    public String toString(){
        return getStatusIcon() + " " + this.description;
    }

    public void markDone(){
        this.isDone = true;
    }
    public void unmark(){
        this.isDone = false;
    }

}