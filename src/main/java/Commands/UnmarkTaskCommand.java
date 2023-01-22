package Commands;

import Exceptions.DukeException;
import Files.Storage;
import Tasks.TaskList;
import Ui.Ui;

public class UnmarkTaskCommand extends Command {

    private String taskIndex;

    public UnmarkTaskCommand(String taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.unmarkTask(taskIndex);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
