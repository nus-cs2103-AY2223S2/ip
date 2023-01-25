package main.java.duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    
    public String toString() {
        return "[T]" + getStatusIcon() + " " + description;
    }
}
