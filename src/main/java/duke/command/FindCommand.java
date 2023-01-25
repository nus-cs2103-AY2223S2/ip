package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String taskname;
    public FindCommand(String taskname) {
        super(false);
        this.taskname = taskname;
    }
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        ui.printFindList(task.findTask(taskname));
    }
}
