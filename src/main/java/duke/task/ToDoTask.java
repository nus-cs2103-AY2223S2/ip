package duke.task;

public class ToDoTask extends Task {

    private static final String EVENT_SYMBOL = "T";

    public ToDoTask(String description) {
        super(description, EVENT_SYMBOL);
    }

}
