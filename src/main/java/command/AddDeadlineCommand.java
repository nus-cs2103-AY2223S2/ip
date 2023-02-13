package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Deadline;
import userinteraction.Ui;
public class AddDeadlineCommand extends AddTaskCommand{
    public AddDeadlineCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = Deadline.generate(this.getInput());
        taskList.addTask(deadline);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, deadline);
    }
}

