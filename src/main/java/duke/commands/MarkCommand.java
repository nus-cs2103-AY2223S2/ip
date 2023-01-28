package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int num;
    private final boolean isMarking;
    public MarkCommand(int num, boolean isMarking) {
        this.num = num;
        this.isMarking = isMarking;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isMarking) {
            tasks.markTask(num);
            ui.display(String.format(
                    "Nice! I've marked this task as done:\n %s", tasks.get(num)));
        } else {
            tasks.unmarkTask(num);
            ui.display(String.format(
                    "Saddd! I've unmarked this task as done:\n %s", tasks.get(num)));
        }

        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }
    }
}
