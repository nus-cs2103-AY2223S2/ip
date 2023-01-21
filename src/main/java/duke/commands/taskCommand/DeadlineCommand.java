package duke.commands.taskCommand;

import java.util.List;

import commands.Command;
import duke.Duke;
import duke.tasks.Deadline;
import duke.tasks.Task;
import utils.Utils;

public class DeadlineCommand extends Command {

  public DeadlineCommand() {
    super("deadline");
  }

  @Override
  protected void execute(String[] args, Duke instance) {
    int index = -1;
    for(int i = 1; i < args.length; i++) {
        if (args[i].equalsIgnoreCase("/by")) {
            index = i;
            break;
        }
    }

    if (index == -1) {
        output("Expected a /by directive!");
        return;
    } else if (index == 1) {
        output("Expected a task!");
        return;
    } else if (index == args.length - 1) {
        output("Expected a time after /by!");
        return;
    }
    String taskStr = Utils.joiner(args, 1, index);
    String time = Utils.joiner(args, index + 1);
    List<Task> tasks = instance.getTaskList();
    Task task = new Deadline(taskStr, time);
  }
  
}
