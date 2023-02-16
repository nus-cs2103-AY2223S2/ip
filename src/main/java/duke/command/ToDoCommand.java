package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.ToDo;

public class ToDoCommand extends Command {
    String command;

    public ToDoCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task newTask;
            String description = command.substring(command.indexOf(" ") + 1);

            if (description.equals("")) {
                throw new DukeException("todo");
            } else {
                newTask = new ToDo(command.substring(command.indexOf(" ") + 1));
            }
            taskList.addTask(newTask);
            ui.showTaskAdded(newTask);
        } catch (DukeException e) {
            e.WrongCommandException();
            e.EmptyDescriptionException();
        }

    }
}

