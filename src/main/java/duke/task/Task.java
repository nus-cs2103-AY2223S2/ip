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

    /**
     * Checks if the task description contains a specifed keyword.
     *
     * @param keyword Keyword to check if it is in description.
     * @return True if the keyword is contained, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString(){
        return isDone ? "[X] " + description : "[ ] " + description;
    }
}
