package commands;

import java.io.IOException;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;


/**
 * Represents command for adding a Deadline
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(String string) {
        super(string);
    }

    /**
     * Execute deadline command
     * @param tasks the current list of tasks
     * @param ui the user interface
     * @param storage the storage where the changes done by command action stored
     * @throws IOException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            String[] request = super.getCommand().split("/", 2);
            String task = request[0];
            String date = request[1];
            Deadline deadline = new Deadline(task, date);
            storage.deadline(task, date);
            tasks.add(deadline);
            ui.addTaskMsg();
            ui.printTask(deadline);
            ui.printListSize(tasks);
        } catch (Exception e) {
            ui.taskErrorMsg();
        }
    }
}
