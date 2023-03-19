package commands;

import java.io.IOException;

import exceptions.EmptyTaskException;
import storage.Storage;
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

    /**
     * @param tasks
     * @param ui
     * @param storage
     * @param idx index of task
     */
    public void replace(TaskList tasks, Ui ui, Storage storage, int idx) {
        try {
            if (super.getCommand().equals("")) {
                throw new EmptyTaskException("The task cant be empty");
            }
            String[] request = super.getCommand().split("/", 3);
            String task = request[0];
            String from = request[1];
            String to = request[2];
            Event event = new Event(task, from, to);
            tasks.set(idx, event);
            storage.replaceEvent(idx, task, from, to);
        } catch (Exception e) {
            ui.evenErrMsg();
        }
    }

    /**
     * generate the action to the save file
     * @param tasks
     * @param ui
     * @param storage
     * @return the string line that will be concatenated to save file
     */
    public String generate(TaskList tasks, Ui ui, Storage storage) {
        String[] request = super.getCommand().split("/", 3);
        String task = request[0];
        String from = request[1];
        String to = request[2];
        Event event = new Event(task, from, to);
        return ui.printAddTask() + event.toString();
    }
}
