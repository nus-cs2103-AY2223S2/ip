package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskAssigner;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class AddCommand extends Command {

    protected TaskAssigner taskAssigner;
    public AddCommand(String textCmd) {
        super(textCmd);
        this.taskAssigner = new TaskAssigner();
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task addedTask = taskAssigner.assignTask(textCmd);
        taskList.add(addedTask);
        return ui.printAddTask(addedTask, taskList.getNumTasks());
    }
}
