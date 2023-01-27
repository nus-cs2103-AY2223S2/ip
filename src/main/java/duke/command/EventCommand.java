package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Task;
import duke.ui.Ui;
import duke.exception.DukeException;


public class EventCommand extends Command {
    protected String taskName;
    protected String from;
    protected String to;

    public EventCommand(String taskName, String from, String to) {
        this.taskName = taskName;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task newTask = new Event(taskName, from, to);
        ui.print(taskList.addTask(newTask));
        storage.saveTaskList(taskList);
    }
}
