package duke.command;

import duke.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(this.idx);
            storage.saveTasklistToFile(tasks);

            setOutput("Hah gomi~ Don't need this anymore~",
                      task.toString(),
                      "Now you have " + tasks.getSize() + " tasks!");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }
}
