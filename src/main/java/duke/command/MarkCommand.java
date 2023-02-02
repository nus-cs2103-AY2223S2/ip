package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {
    
    public MarkCommand(String[] contents) {
        super(contents,false);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int userMarkIndex = Integer.parseInt((super.contents)[0]) - 1;
            Task currentTask = taskList.getTasks().get(userMarkIndex);
            currentTask.markAsDone();
            ui.displayResult(TypeOfTask.mark, currentTask, taskList);
        } catch (Exception e) {
            throw new DukeException(TypeOfTask.mark,1);
        }
    }
}
