package sam.command;

import java.util.List;

import sam.Ui;
import sam.storage.Storage;
import sam.task.TaskList;

public class ListCommand extends Command {
  public ListCommand(String args) {
    super(args);
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    if (tasks.count() == 0) {
      ui.talk("Your list is empty!");
    } else {
      List<String> list = tasks.generateList();
      list.add(0, "Here is your list:");
      ui.talk(list.toArray(new String[0]));
    }
  }  
}
