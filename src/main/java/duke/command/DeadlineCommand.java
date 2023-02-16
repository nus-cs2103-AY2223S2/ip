package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.Deadline;

public class DeadlineCommand extends Command {
    String command;

    public DeadlineCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage,  Ui ui) throws DukeException {
        try {
            Task newTask;
            String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/"));
            String by = command.substring(command.indexOf("/by") + 4);

            if (description.equals("")) {
                throw new DukeException("deadline");
            } else if (by.equals("")) {
                throw new DukeException("empty time");
            } else {
                newTask = new Deadline(description, by);
            }

            taskList.addTask(newTask);
            ui.showTaskAdded(newTask);
        } catch (DukeException e) {
            e.WrongCommandException();
            e.EmptyDescriptionException();
            e.EmptyTimeException();
        }

    }
}