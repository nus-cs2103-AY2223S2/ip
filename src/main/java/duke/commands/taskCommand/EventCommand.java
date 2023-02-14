package duke.commands.taskcommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.Utils;
import duke.parser.Arguments;
import duke.task.Event;

public class EventCommand extends TaskCommand<Event> {
    public EventCommand() {
        super("event");
    }

    @Override
    protected Event getTask(Arguments args, Duke instance) throws ValidationException {
        int priority = getPriorityFromArgs(args);
        
        if (!args.hasLabelledArgument("to")) {
            throw new ValidationException("Expected a /to directive!");
        }
        String[] toArgs = args.getLabelledArgument("to");
        validate(toArgs.length > 0, "Expected a time after /to!");

        if (!args.hasLabelledArgument("from")) {
            throw new ValidationException("Expected a /from directive!");
        }

        String[] fromArgs = args.getLabelledArgument("from");
        validate(fromArgs.length > 0, "Expected a time after /from!");

        if (args.getExcessArguments() == null) {
            throw new ValidationException("Expected a task!");
        }
        String[] taskArgs = args.getExcessArguments();

        try {
            LocalDateTime fromTime = Utils.parseDateTime(
                fromArgs[0],
                fromArgs.length < 2 ? null : fromArgs[1]
            );
            LocalDateTime toTime = Utils.parseDateTime(
                toArgs[0], 
                toArgs.length < 2 ? null : toArgs[1]
            );

            validate(fromTime.isBefore(toTime), " I can't create an event that ends before it starts!");

            String taskStr = Utils.stringJoiner(taskArgs, 0);
            return new Event(taskStr, priority, fromTime, toTime);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Failed to parse the date you've given: %s\n", e.getParsedString());
        }
    }
}
