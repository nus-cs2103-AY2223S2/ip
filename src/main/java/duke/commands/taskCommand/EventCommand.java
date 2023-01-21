package duke.commands.taskCommand;

import java.util.Optional;

import duke.Duke;
import duke.tasks.Event;
import duke.tasks.Task;
import utils.Utils;

public class EventCommand extends TaskCommand {
  public EventCommand() {
    super("event");
  }

  @Override
  protected Optional<Task> getTask(String[] args, Duke instance) {
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
        System.out.println("Expected a /from directive!");
        return Optional.empty();
    } else if (toIndex == -1) {
        // No /to provided
        System.out.println("Expected a /to directive!");
        return Optional.empty();
    } else if (fromIndex == 1) {
        // From was the first keyword after event
        System.out.println("Expected a task!");
        return Optional.empty();
    } else if (fromIndex > toIndex) {
        System.out.println("Expected /from to come before /to!");
        return Optional.empty();
    } else if (toIndex == args.length - 1) {
        System.out.println("Expected a time after /to!");
        return Optional.empty();
    } else if (toIndex - fromIndex == 1) {
        System.out.println("Expected a time after /from!");
        return Optional.empty();
    }

    String taskStr = Utils.joiner(args, 1, fromIndex);
    String fromStr = Utils.joiner(args, fromIndex + 1, toIndex);
    String toStr = Utils.joiner(args, toIndex + 1, args.length);

    return Optional.of(new Event(taskStr, fromStr, toStr));
  }
}
