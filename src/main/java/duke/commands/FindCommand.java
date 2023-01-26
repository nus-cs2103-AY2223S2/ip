package duke.commands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.Duke;
import duke.Utils;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {

  public FindCommand() {
    super("find");
  }

  @Override
  protected void execute(String[] tokens, Duke instance) throws ValidationException {
    validate(tokens.length > 1, "Need a string to search for!");

    TaskList tasks = instance.getTaskList();
    String searchStr = Utils.stringJoiner(tokens, 1);

    List<Task> filteredTasks = tasks.stream().filter(t -> {
      String desc = t.getTask();
      return desc.contains(searchStr);
    })
    .collect(Collectors.toUnmodifiableList());

    if (filteredTasks.size() == 0) {
      output("Could not find any tasks that matched '%s'", searchStr);
    } else {
      output(IntStream.range(0, filteredTasks.size())
        .boxed()
        .map(i -> String.format("%d. %s", i + 1, filteredTasks.get(i)))
        .collect(Collectors.joining(
          "\n", 
          String.format("%d tasks matched '%s':\n", filteredTasks.size(), searchStr),
          "\n"
      )));
    }
  }
}
