package duke.command;

import duke.DukeException;

public class ListTasksCommand extends Command {
    public ListTasksCommand() {
    }

    @Override
    public void execute() throws DukeException {
        ui.printTaskList(taskList);
    }

}
