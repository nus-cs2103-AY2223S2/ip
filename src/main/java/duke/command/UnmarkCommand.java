package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.event.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    
    public UnmarkCommand(String[] contents) {
        super(contents, false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int userMarkIndex = Integer.parseInt((super.contents)[0]) - 1;
        Task currentTask = taskList.getTasks().get(userMarkIndex);
        currentTask.unmarkAsDone();
        ui.displayResult(TypeOfTask.unmark, currentTask, taskList);
    }
}
