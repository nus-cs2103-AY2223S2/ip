package command;

import aqua.exception.DukeIllegalArgumentException;
import task.Deadline;

public class DeadlineTaskCreator implements TaskCreator {
    @Override
    public Deadline apply(CommandInput input) throws DukeIllegalArgumentException {
        String name = input.getMainInput()
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing task name"));
        String by = input.get("by")
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing by parameter"));

        return new Deadline(name, by);
    }
}
