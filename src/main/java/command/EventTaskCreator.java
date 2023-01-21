package command;

import aqua.exception.IllegalSyntaxException;
import task.Event;


public class EventTaskCreator implements TaskCreator {
    @Override
    public Event apply(CommandInput input) throws IllegalSyntaxException {
        String name = input.getMainInput()
            .orElseThrow(() -> new IllegalSyntaxException("Missing task name"));
        String from = input.get("from")
            .orElseThrow(() -> new IllegalSyntaxException("Missing from parameter"));
        String to = input.get("to")
            .orElseThrow(() -> new IllegalSyntaxException("Missing to parameter"));

        return new Event(name, from, to);
    }
}
