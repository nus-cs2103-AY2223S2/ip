package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.Event;

public class EventCommand extends Command {
    String command;

    public EventCommand(String command) {
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task newTask;
            String description = command.substring(command.indexOf(" ") + 1, command.indexOf("/from"));
            String from = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
            String to = command.substring(command.indexOf("/to") + 4);

            if (description.equals("")) {
                throw new DukeException("event");
            } else if (from.equals("") || to.equals("")) {
                throw new DukeException("empty time");
            } else {
                newTask = new Event(description, from, to);
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