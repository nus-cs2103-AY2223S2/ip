package command;

import aqua.exception.IllegalSyntaxException;
import task.Deadline;

public class DeadlineTaskCreator implements TaskCreator {
    @Override
    public Deadline apply(CommandInput input) throws IllegalSyntaxException {
        String name = input.getMainInput()
            .orElseThrow(() -> new IllegalSyntaxException("Missing task name"));
        String by = input.get("by")
            .orElseThrow(() -> new IllegalSyntaxException("Missing by parameter"));

        return new Deadline(name, by);
    }
}
