package command;

import aqua.exception.DukeIllegalArgumentException;
import task.Event;


public class EventTaskCreator implements TaskCreator {
    @Override
    public Event apply(CommandInput input) throws DukeIllegalArgumentException {
        String name = input.getMainInput()
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing task name"));
        String from = input.get("from")
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing from parameter"));
        String to = input.get("to")
            .orElseThrow(() -> new DukeIllegalArgumentException("Missing to parameter"));

        return new Event(name, from, to);
    }
}
