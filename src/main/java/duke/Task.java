package duke;
public abstract class Task {

    public static final String SEPARATOR = " | ";
    String description;
    boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public String convertBoolean() {
        return (this.isDone) ? "1" : "0";
    }
    public String getDescription() {
        return description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }

    public abstract String toSave();
}

