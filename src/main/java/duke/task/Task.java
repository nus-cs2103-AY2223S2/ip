package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks this Task as done.
     */
    public void markDone(){
        this.isDone = true;
    }

    /**
     * Marks this Task as not done.
     */
    public void markUndone(){
        this.isDone = false;
    }

    /**
     * Formats Task as a string to be saved.
     *
     * @return Formatted string description of the task.
     */
    public abstract String save();

    @Override
    public String toString(){
        return isDone ? "[X] " + description : "[ ] " + description;
    }
}
