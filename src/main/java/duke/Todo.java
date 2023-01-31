package duke;

public class Todo extends Task {

    protected String icon = "[T]";

    public Todo(String description, Boolean isDone) {
        super(description, 'T', isDone);
    }

    /**
     * Returns a string representation of this To-Do task
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}