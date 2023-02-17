package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents command for adding an event
 */
public class EventCommand extends Command {
    public EventCommand(String string) {
        super(string);
    }

    /**
     * Execute adding Event command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            String[] request = super.getCommand().split("/", 3);
            String task = request[0];
            String from = request[1];
            String to = request[2];
            Event event = new Event(task, from, to);
            tasks.add(event);
            storage.event(task, from, to);
            ui.addTaskMsg();
            ui.printTask(event);
            ui.printListSize(tasks);
        } catch (Exception e) {
            ui.taskErrorMsg();
        }
    }

    public String generate(TaskList tasks, Ui ui, Storage storage) {
        String[] request = super.getCommand().split("/", 3);
        String task = request[0];
        String from = request[1];
        String to = request[2];
        Event event = new Event(task, from, to);
        return ui.printAddTask() + event.toString();
    }
}
