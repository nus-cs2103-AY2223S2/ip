package commands;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class DeadlineCommand extends Command {
    public DeadlineCommand(String string) {
        super(string);
    }

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
