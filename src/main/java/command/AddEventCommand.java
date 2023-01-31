package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import task.Event;
import userinteraction.Ui;

public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String[] inputArr) {
        super(inputArr);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = Event.generate(inputArr);
        taskList.addTask(event);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, event);
    }
}
