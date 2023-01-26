package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.DukeException;
import duke.task.Todo;

public class ToDoCommand extends Command {

    public ToDoCommand(String[] command) {
        super(command);
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (command.length == 1) {
                throw new DukeException(null, null);
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < command.length; i++) {
                stringBuilder.append(command[i]);
                if (i + 1 != command.length) {
                    stringBuilder.append(" ");
                }
            }
            tasks.add(new Todo(stringBuilder.toString()));
            ui.addMsg(tasks);
            storage.saveToDisk(tasks);
        } catch (DukeException e) {
            ui.todoError();
        }
    }
}