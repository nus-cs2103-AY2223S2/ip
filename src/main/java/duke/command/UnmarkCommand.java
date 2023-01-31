package duke.command;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskNo;

    public UnmarkCommand(int n) {
        this.taskNo = n;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.printToFormat("Marked as yet to complete:\n    " + tasks.unmark(taskNo));
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
