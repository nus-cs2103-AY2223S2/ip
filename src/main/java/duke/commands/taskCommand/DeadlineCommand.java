package duke.commands.taskCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Duke;
import duke.Utils;
import duke.task.Deadline;

public class DeadlineCommand extends TaskCommand<Deadline> {
  public DeadlineCommand() {
    super("deadline");
  }

  @Override
  protected Deadline getTask(String[] args, Duke instance) throws ValidationException {
    int index = -1;
    for(int i = 1; i < args.length; i++) {
      if (args[i].equalsIgnoreCase("/by")) {
        index = i;
        break;
      }
    }

    validate(index != -1, "Expected a /by directive!");
    validate(index != 1, "Expected a task!");
    validate(index != args.length - 1, "Expected a time after /by!");

    try {
      LocalDateTime deadline = Utils.parseDateTime(args[index + 1], index + 2 >= args.length ? null : args[index + 2]);
      validate(deadline.isAfter(LocalDateTime.now()), "I can't create something's that's due in the past!");

      String taskStr = Utils.stringJoiner(args, 1, index);
      return new Deadline(taskStr, deadline);
    } catch (DateTimeParseException e) {
      throw new ValidationException(String.format("Error parsing deadline: %s\n", e.getMessage()));
    }
  }
}
