package duke.tasks;

import duke.exceptions.DukeTodoNoDescription;

public class Todo extends Task {

    private static final String PREFIX = "T";

    public Todo(String description) throws DukeTodoNoDescription {
        super(description);
    }

    @Override
    public String getPrefix() {
        return PREFIX;
    }

    @Override
    public String save() {
        StringBuilder response = new StringBuilder("");
        response.append(getPrefix() + ",");
        response.append(isDone + ",");
        response.append(description + "\n");
        return response.toString();
    }
}