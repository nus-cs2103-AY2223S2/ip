package command;

import java.util.function.Function;

import task.Event;


public class EventTaskCreator implements Function<CommandInput, Event> {
    @Override
    public Event apply(CommandInput input) {
        String name = input.getMainInput()
            .orElseThrow(() -> new IllegalArgumentException("Missing task name"));
        String from = input.get("from")
            .orElseThrow(() -> new IllegalArgumentException("Missing from parameter"));
        String to = input.get("to")
            .orElseThrow(() -> new IllegalArgumentException("Missing to parameter"));

        return new Event(name, from, to);
    }
}
