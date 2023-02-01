package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * Executable command to create event task.
 *
 * @author Guo-KeCheng
 */
public class EventCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    /**
     * EventCommand constructor
     *
     * @param command Entire line of user input
     * @param taskList Existing taskList
     * @param ui Shared Ui object
     */
    public EventCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Instantiate event base on the string command
     * Checks for valid TaskName and EndDate
     *
     * @return false as program will not terminate yet
     * @throws DukeException if input is incorrect
     */
    @Override
    public boolean execute() throws DukeException {
        String taskName = getTaskName("event", command);
        String startDate = getStartDate(command);
        String endDate = getEndDate("event", command);

        try {
            Event event = new Event(taskName, startDate, endDate);
            taskList.add(event);
            ui.printAddedTask(event, taskList);
        } catch (DateTimeParseException e) {
            ui.showInvalidTimeError();
        }

        return false;
    }
}
