package duke;

class Task {
    
    private final boolean isDone; 
    private final String description;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
     
    Task markAsDone() {
        return new Task(this.description, true);
    }

    Task markAsUndone() {
        return new Task(this.description, false);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    String getDescription() {
        return this.description;
    }

    @Override 
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(), this.description);
    }

}

