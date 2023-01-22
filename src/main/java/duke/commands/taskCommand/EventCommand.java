package duke.commands.taskCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.Duke;
import duke.Utils;
import duke.task.Event;

public class EventCommand extends TaskCommand<Event> {
  public EventCommand() {
    super("event");
  }

  @Override
  protected Optional<Event> getTask(String[] args, Duke instance) {
    int fromIndex = -1, toIndex = -1;
    for(int i = 1; i < args.length; i++) {
        if (args[i].equalsIgnoreCase("/from")) {
            fromIndex = i;
        } else if (args[i].equalsIgnoreCase("/to")) {
            toIndex = i;
        }

        if (fromIndex != -1 && toIndex != -1) break;
    }

    if (fromIndex == -1) {
        // No /from provided
        output("Expected a /from directive!");
        return Optional.empty();
    } else if (toIndex == -1) {
        // No /to provided
        output("Expected a /to directive!");
        return Optional.empty();
    } else if (fromIndex == 1) {
        // From was the first keyword after event
        output("Expected a task!");
        return Optional.empty();
    } else if (fromIndex > toIndex) {
        output("Expected /from to come before /to!");
        return Optional.empty();
    } else if (toIndex == args.length - 1) {
        output("Expected a time after /to!");
        return Optional.empty();
    } else if (toIndex - fromIndex == 1) {
        output("Expected a time after /from!");
        return Optional.empty();
    }

    try {
        LocalDateTime fromTime = Utils.parseDateTime(args[fromIndex + 1], toIndex - fromIndex == 2 ? null : args[fromIndex + 2]);
        LocalDateTime toTime = Utils.parseDateTime(args[toIndex + 1], toIndex + 2 >= args.length ? null : args[toIndex + 2]);

        if (fromTime.isBefore(toTime)) {
            output("I can't create an event that ends before it starts!");
            return Optional.empty();
        }

        String taskStr = Utils.stringJoiner(args, 1, fromIndex);
        return Optional.of(new Event(taskStr, fromTime, toTime));
    } catch (DateTimeParseException e) {
        output("Failed to parse the date you've given: %s\n", e.getParsedString());
        return Optional.empty();
    }
  }
}
