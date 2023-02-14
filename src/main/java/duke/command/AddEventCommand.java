package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.textui.TextUi;

/**
 * A command that stores the command to add a new event task. The action of
 * adding the task can be carried out when called.
 */
public class AddEventCommand extends Command {
    /**
     * The DESCRIPTION, start and end date of the event task.
     */
    private final String data;

    /**
     * Constructor for a command to add new event task.
     *
     * @param data The DESCRIPTION, start and end date of the event task
     */
    public AddEventCommand(String data) {
        super(AvailableCommands.ADD_EVENT);
        this.data = data;
    }

    /**
     * Adds a new event task into the task list.
     * The start and end dates are filtered out. If they exist, then an event task
     * will be created. Otherwise, an exception would be thrown stating that
     * either the start or end dates were not specified.
     *
     * @param taskList List of tasks that are stored
     * @param ui       UI to deal with the visual output
     * @param storage  Storage to deal with input and output of data
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage)
            throws DukeException {
        String[] splitData1 = data.split(" /from ", 2);
        if (splitData1.length < 2) {
            throw new DukeException("Event command format error. Missing /from");
        }

        assert splitData1.length == 2;

        String[] splitData2 = splitData1[1].split(" /to ", 2);
        if (splitData2.length < 2) {
            throw new DukeException("Event command format error. Missing /to");
        }

        assert splitData2.length == 2;

        Task event = new Event(splitData1[0], splitData2[0], splitData2[1]);
        taskList.addTask(event);

        return ui.showAddTask(event.toString(), taskList.size());
    }
}
