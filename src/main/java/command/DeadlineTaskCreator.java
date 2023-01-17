package command;

import java.util.function.Function;

import task.Deadline;

public class DeadlineTaskCreator implements Function<CommandInput, Deadline> {
    @Override
    public Deadline apply(CommandInput input) {
        String name = input.getMainInput()
            .orElseThrow(() -> new IllegalArgumentException("Missing task name"));
        String by = input.get("by")
            .orElseThrow(() -> new IllegalArgumentException("Missing by parameter"));

        return new Deadline(name, by);
    }
}
