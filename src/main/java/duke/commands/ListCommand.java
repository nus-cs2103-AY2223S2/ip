package duke.commands;

import java.util.List;

import duke.Duke;
import duke.task.Task;

public class ListCommand extends Command{

  public ListCommand() {
    super("list");
  }

  @Override
  protected void execute(String[] tokens, Duke instance) {
    List<Task> tasks = instance.getTaskList();

    if (tasks.size() == 0) {
      output("No stored tasks!");
    } else {
      output("A total of %d tasks\n", tasks.size());
      for (int i = 0; i < tasks.size(); i++) {
        output("%d. %s\n", i + 1, tasks.get(i).toString());
      }
    }
  }
}
