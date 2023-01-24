package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Todo;

public class CreateTodo extends Command {
   private String desc;
   
   public CreateTodo(String desc) {
      this.desc = desc;
   }

   @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(new Todo(desc, false));
        ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
