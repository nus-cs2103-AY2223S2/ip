package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineCommand extends Command {
    private final String DATA;

    public AddDeadlineCommand(String commandString, String DATA) {
        super(Commands.ADD_DEADLINE, commandString);
        this.DATA = DATA;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] splitData = this.DATA.split(" /by ", 2);
        if (splitData.length < 2) {
            throw new DukeException("Deadline command format error. Missing /by");
        }

        Task deadline = new Deadline(splitData[0], splitData[1]);
        tasks.addTask(deadline);

        ui.showAddTask(deadline.toString(), tasks.size());
    }
}
