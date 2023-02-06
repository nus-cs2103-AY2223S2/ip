package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

public class CreateTodo extends Command {
   private String desc;
   
   public CreateTodo(String desc) {
      this.desc = desc;
   }

   @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Integer size = tasks.size();
        tasks.add(new Todo(desc, false));
        assert size + 1 == tasks.size();
        return ui.showAdd(tasks.get(tasks.size() - 1), tasks.size());
    }
}
