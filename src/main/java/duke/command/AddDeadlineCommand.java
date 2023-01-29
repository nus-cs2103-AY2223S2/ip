package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineCommand extends Command {
    private final String DATA;

    public AddDeadlineCommand(String commandString, String data) {
        super(AvailableCommands.ADD_DEADLINE, commandString);
        DATA = data;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] splitData = DATA.split(" /by ", 2);
        if (splitData.length < 2) {
            throw new DukeException("Deadline command format error. Missing /by");
        }

        Task deadline = new Deadline(splitData[0], splitData[1]);
        taskList.addTask(deadline);

        ui.showAddTask(deadline.toString(), taskList.size());
    }
}
