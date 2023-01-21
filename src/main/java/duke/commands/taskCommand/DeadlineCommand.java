package duke.commands.taskCommand;

import java.util.Optional;

import duke.Duke;
import duke.tasks.Deadline;
import duke.tasks.Task;
import utils.Utils;

public class DeadlineCommand extends TaskCommand {
  public DeadlineCommand() {
    super("deadline");
  }

  @Override
  protected Optional<Task> getTask(String[] args, Duke instance) {
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
    String taskStr = Utils.joiner(args, 1, index);
    String time = Utils.joiner(args, index + 1);
    return Optional.of(new Deadline(taskStr, time));
  }
}
