package duke.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Executable command to create event task.
 *
 * @author Guo-KeCheng
 */
public class EventCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * EventCommand constructor
     *
     * @param input    Entire line of user input
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public EventCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Instantiate event base on the string command
     * Checks for valid TaskName and EndDate
     *
     * @return false as program will not terminate yet
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        String taskName = getTaskName("event", input);
        String startDate = getStartDate(input);
        String endDate = getEndDate("event", input);


        try {
            Event event = new Event(taskName, startDate, endDate);
            taskList.add(event);

            try {
                storage.save(taskList);
            } catch (IOException e) {
                return ui.showError(e.getMessage());
            }

            return ui.printAddedTask(event, taskList);
        } catch (DateTimeParseException e) {
            return ui.showInvalidTimeError();
        }

    }
}
