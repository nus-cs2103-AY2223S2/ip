package commands;

import exceptions.InvalidRequestException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents command for adding an event
 */
public class EventCommand extends Command {
    public EventCommand(String string) {
        super(string);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            String[] request = super.getCommand().split("/", 3);
            String task = request[0];
            String from = request[1];
            String to = request[2];
            Event event = new Event(task, from, to);
            storage.event(task, from, to);
            tasks.add(event);
            ui.addTaskMsg();
            ui.printTask(event);
            ui.printListSize(tasks);
        } catch (Exception e) {
            ui.taskErrorMsg();
        }
    }
}
