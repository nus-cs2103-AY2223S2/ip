package duke.command;

import duke.tasks.Task;
import duke.TaskAssigner;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddCommand extends Command {

    protected TaskAssigner taskAssigner;
    public AddCommand(String textCmd) {
        super(textCmd);
        this.taskAssigner = new TaskAssigner();
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task to_add = taskAssigner.assignTask(textCmd);
        taskList.add(to_add);
        return ui.printAddTask(to_add, taskList.getNumTasks());
    }
}
