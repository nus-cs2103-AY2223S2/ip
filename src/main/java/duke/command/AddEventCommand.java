package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

public class AddEventCommand extends Command {
    private final String DATA;

    public AddEventCommand(String commandString, String data) {
        super(AvailableCommands.ADD_EVENT, commandString);
        DATA = data;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] splitData1 = DATA.split(" /from ", 2);
        if (splitData1.length < 2) {
            throw new DukeException("Event command format error. Missing /from");
        }

        String[] splitData2 = splitData1[1].split(" /to ", 2);
        if (splitData2.length < 2) {
            throw new DukeException("Event command format error. Missing /to");
        }

        Task event = new Event(splitData1[0], splitData2[0], splitData2[1]);
        taskList.addTask(event);

        ui.showAddTask(event.toString(), taskList.size());
    }
}
