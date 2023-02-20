package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command the task to be added in the list.
     */
    public EventCommand(String command) {
        this.command = command;
        assert command.toLowerCase().substring(0, command.indexOf(" ")).equals("event");
    }

    /**
     * Adds a new event task to the list and returns a "taskAdded" message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskAdded" message.
     * @throws DukeException if input is wrong.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task newTask;
            String[] commands = command.split(" ", 2);
            String description = commands[1].substring(0, commands[1].indexOf("/from"));
            String stringFrom = commands[1].substring(commands[1].indexOf("/from") + 6, commands[1].indexOf(" /to"));
            String stringTo = commands[1].substring(commands[1].indexOf("/to") + 4);

            if (description.equals("")) {
                throw new DukeException("OOPS!!! The description of an event task cannot be empty.");
            } else if (stringFrom.equals("") || stringTo.equals("")) {
                throw new DukeException("Please specify the time the time period for this task.");
            } else {
                try {
                    LocalDateTime from = LocalDateTime.parse(stringFrom, DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
                    LocalDateTime to = LocalDateTime.parse(stringTo, DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"));
                    assert !(description.equals("")) || !(from.equals("")) || !(to.equals(""));
                    newTask = new Event(description, from, to);
                } catch (Exception e) {
                    throw new DukeException("The time period must be in the format: 'HH:mm dd-MM-yyyy'");
                }
            }

            taskList.addTask(newTask);
            return ui.showTaskAdded(newTask);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
    }
}