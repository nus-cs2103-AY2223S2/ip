package duke.commands;

import java.util.List;
import java.util.stream.IntStream;

import commands.Command;
import duke.Duke;
import duke.tasks.Task;

public class ListCommand extends Command {
  public ListCommand() {
    super("list");
  }

  @Override
  protected void execute(String[] tokens, final Duke instance) {
    List<Task> tasks = instance.getTaskList();

    if (tasks.size() == 0) {
       output("You have no tasks!");
    } else {
      output("A total of %d tasks\n", tasks.size());
      IntStream.range(0, tasks.size())
        .forEach(i -> output("%d. %s\n", i + 1, tasks.get(i).toString()));
    }
  }
}
