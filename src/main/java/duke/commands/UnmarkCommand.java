package duke.commands;

import java.util.List;

import duke.Duke;
import duke.task.Task;

public class UnmarkCommand extends Command {

  public UnmarkCommand() {
    super("unmark");
  }

  @Override
  protected void execute(String[] args, Duke instance) {
    List<Task> tasks = instance.getTaskList();
    try {
      if (args.length == 1) {
        output("Needed an index for unmark");
        return;
      }

      int index = Integer.parseInt(args[1]);
      if (index < 1 || index > tasks.size()) throw new NumberFormatException();

      tasks.get(index - 1).setDone(false);
      output("Marked this as not done!\n%s\n", tasks.get(index - 1).toString());
    } catch (NumberFormatException e) {
      output("Invalid index!\n");
    }
  }
}