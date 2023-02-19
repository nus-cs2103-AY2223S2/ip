package duke.command;

import duke.exceptions.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidIndexException;

public class MarkCommand extends Command {
    private final boolean isMarked;
    private final int idx;

    public MarkCommand(boolean isMarked, int idx) {
        this.isMarked = isMarked;
        this.idx = idx - 1;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            if (this.isMarked) {
                tasks.get(this.idx).markAsDone();
                setOutput("Yatta! You have done this task!", tasks.get(this.idx).toString());
            } else {
                tasks.get(this.idx).unmarkAsDone();
                setOutput("Neee! Are you kidding me?", tasks.get(this.idx).toString());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }

        storage.saveTasklistToFile(tasks);
    }
}
