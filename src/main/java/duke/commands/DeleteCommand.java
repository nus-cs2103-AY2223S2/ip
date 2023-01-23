package duke.commands;

import java.util.List;

import duke.Duke;
import duke.task.Task;

public class DeleteCommand extends Command {
  public DeleteCommand() {
    super("delete");
  }

  @Override
  protected void execute(String[] args, Duke instance) throws ValidationException {
    try {
        validate(args.length > 1, "Needed an index for delete");

        int index = Integer.parseInt(args[1]);
        List<Task> tasks = instance.getTaskList();
        Task removed = tasks.remove(index - 1);
        output("Removed this task!\n%s\n", removed.toString());
    } catch (NumberFormatException e) {
        throw new ValidationException("Invalid index!");
    }
  }
}
