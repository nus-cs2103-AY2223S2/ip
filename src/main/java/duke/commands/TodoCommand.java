package duke.commands;

import duke.storage.Todos;
import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

import java.io.IOException;

public class TodoCommand extends Command {
    private final Todos todo;
    public TodoCommand(Todos todo) {
        this.todo = todo;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.todo);
        ui.display("Got it. I've added this task:\n" + this.todo +
                String.format("\nNow you have %s tasks in the list.", tasks.size()));

        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }

    }
}
