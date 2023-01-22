package duke.commands;

import java.util.List;

import commands.Command;
import duke.Duke;
import duke.tasks.Task;

public class DeleteCommand extends Command {
  public DeleteCommand() {
    super("delete");
  }

  @Override
  protected void execute(String[] args, Duke instance) {
    try {
        if (args.length == 1) {
            output("Needed an index for delete");
            return;
        }

        int index = Integer.parseInt(args[1]);
        List<Task> tasks = instance.getTaskList();
        Task removed = tasks.remove(index - 1);
        output("Removed this task!\n%s\n", removed.toString());
    } catch (NumberFormatException e) {
        output("Invalid index!");
    }
  }
  
}
