package duke.commands.taskCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.Utils;
import duke.task.Event;

public class EventCommand extends TaskCommand<Event> {
    public EventCommand() {
        super("event");
    }

    @Override
    protected Event getTask(String[] args, Duke instance) throws ValidationException {
        int fromIndex = -1, toIndex = -1;
        for (int i = 1; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("/from")) {
                fromIndex = i;
            } else if (args[i].equalsIgnoreCase("/to")) {
                toIndex = i;
            }

            if (fromIndex != -1 && toIndex != -1) {
                break;
            }
        }

        validate(fromIndex != -1, "Expected a /from directive!");
        validate(toIndex != -1, "Expected a /to directive!");
        int fromLength, toLength;
        if (fromIndex > toIndex) {
            fromLength = args.length - fromIndex - 1;
            toLength = fromIndex - toIndex  - 1;
        } else {
            toLength = args.length - toIndex - 1;
            fromLength = toIndex - fromIndex  - 1;
        }

        validate(Math.min(fromIndex, toIndex) > 1, "Expected a task!");
        validate(toLength > 0, "Expected a time after /to!");
        validate(fromLength > 0, "Expected a time after /from!");

        try {
            LocalDateTime fromTime = Utils.parseDateTime(
                args[fromIndex + 1],
                fromLength < 2 ? null : args[fromIndex + 2]
            );
            LocalDateTime toTime = Utils.parseDateTime(
                args[toIndex + 1], 
                toLength < 2 ? null : args[toIndex + 2]
            );

            validate(fromTime.isBefore(toTime), " I can't create an event that ends before it starts!");

            String taskStr = Utils.stringJoiner(args, 1, Math.min(fromIndex, toIndex));
            return new Event(taskStr, fromTime, toTime);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Failed to parse the date you've given: %s\n", e.getParsedString());
        }
    }
}
