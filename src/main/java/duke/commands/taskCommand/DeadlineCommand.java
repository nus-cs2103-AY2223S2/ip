package duke.commands.taskCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import duke.Duke;
import duke.Utils;
import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand<Deadline> {
  public DeadlineCommand() {
    super("deadline");
  }

  @Override
  protected Optional<Deadline> getTask(String[] args, Duke instance) {
    int index = -1;
    for(int i = 1; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("/by")) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      output("Expected a /by directive!");
      return Optional.empty();
    } else if (index == 1) {
      output("Expected a task!");
      return Optional.empty();
    } else if (index == args.length - 1) {
      output("Expected a time after /by!");
      return Optional.empty();
    }

    try {
      LocalDateTime deadline = Utils.parseDateTime(args[index + 1], index + 2 >= args.length ? null : args[index + 2]);

      if (deadline.isBefore(LocalDateTime.now())) {
        System.out.println("I can't create something's that's due in the past!");
        return Optional.empty();
      }

      String taskStr = Utils.stringJoiner(args, 1, index);
      return Optional.of(new Deadline(taskStr, deadline));
    } catch (DateTimeParseException e) {
      output("Error parsing deadline: %s\n", e.getMessage());
      return Optional.empty();
    }
  }
}
