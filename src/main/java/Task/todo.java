package Task;

import Task.Task;

public class todo extends Task {
    
    public todo(String description) {
        super(description);
    }

    public todo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }
    

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getCommand() {
        return this.isDone
        ? "1 todo " + this.description
        : "0 todo " + this.description;
    }

}
