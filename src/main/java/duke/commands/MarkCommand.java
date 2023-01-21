package duke.commands;

import java.util.List;

import commands.Command;
import duke.Duke;
import duke.tasks.Task;

public class MarkCommand extends Command {
  public MarkCommand() {
    super("mark");
  }

  @Override
  public void execute(String[] args, final Duke instance) {
    try {
      if (args.length == 1) {
        output("Needed an index for mark");
        return;
      }
      
      List<Task> tasks = instance.getTaskList();
      int index = Integer.parseInt(args[1]);

      if (index < 1 || index > tasks.size()) throw new NumberFormatException();

      tasks.get(index - 1).setDone(true);
      output("Marked this as done!\n%s\n", tasks.get(index - 1).toString());
    } catch (NumberFormatException e) {
        output("Invalid index '%s'!\n", args[1]);
    }
  }
}
