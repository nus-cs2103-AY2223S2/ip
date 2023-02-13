package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Event;
import userinteraction.Ui;
public class AddEventCommand extends AddTaskCommand {
    public AddEventCommand(String input) {
        super(input);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = Event.generate(this.getInput());
        taskList.addTask(event);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, event);
    }
}