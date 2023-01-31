package duke.command;

import java.io.IOException;

import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private int taskNo;

    public MarkCommand(int n) {
        this.taskNo = n;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("Marked as completed:\n    " + tasks.mark(taskNo));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            System.out.println("failed to update tasks locally: " + e.getMessage());
        }
    }
}
