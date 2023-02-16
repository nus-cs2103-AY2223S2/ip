package duke.command;

import duke.*;

public class DeleteCommand extends Command {
    private final int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(this.idx);
            storage.saveTasklistToFile(tasks);

            setOutput("Hah gomi~ Don't need this anymore~", task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index yo~");
        }
    }
}
