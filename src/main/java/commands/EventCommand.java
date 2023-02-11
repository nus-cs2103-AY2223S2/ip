package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;
import tasks.Event;

public class EventCommand implements Command {
    private String input;

    public EventCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Event event = new Event(getDescription(), getStartDateTime(), getEndDateTime());
        taskList.getTaskList().add(event);
        storage.store(taskList.getTaskList());
        ui.newEventMessage(event);
    }

    public String getDescription() {
        return input.split(" ", 2)[1].split(" /from ", 2)[0];
    }

    public String getStartDateTime() {
        return input.split(" /from ", 2)[1].split(" /to ", 2)[0];
    }

    public String getEndDateTime() {
        return input.split(" /to ", 2)[1];
    }
}
