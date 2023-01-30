package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    public void markDone(){
        this.isDone = true;
    }

    public void markUndone(){
        this.isDone = false;
    }

    public abstract String save();

    @Override
    public String toString(){
        return isDone ? "[X] " + description : "[ ] " + description;
    }
}
