public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws EmptyDescriptionException{
        if (description.equals("") || description.equals(" ")) {
            throw new EmptyDescriptionException();
        } else {
            this.description = description;
            this.isDone = false;
        }
    }
    public abstract String getFileDescription();
    
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }
}

