package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final boolean isMarked;
    private final int idx;

    public MarkCommand(boolean isMarked, int idx) {
        this.isMarked = isMarked;
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (this.isMarked) {
                tasks.get(this.idx).markAsDone();
                setOutput("Yatta! You have done this task!", tasks.get(this.idx).toString());
            } else {
                tasks.get(this.idx).unmarkAsDone();
                setOutput("Neee! Are you kidding me?", tasks.get(this.idx).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid index yo~");
        }

        storage.saveTasklistToFile(tasks);
    }
}
