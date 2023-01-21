package duke;

/**
 * Todo extends the Task class and represents a simple task description
 * without any information associated with it
 */

public class Todo extends Task {
    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        String s = "[ T ]" + super.toString();
        return s;
    }

    @Override
    public String summary() {
        String s = "T___";
        String d = this.getDone() ? "✓" : "X";
        return s + d + "___" + this.getTask();
    }

}
