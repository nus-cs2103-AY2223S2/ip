package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

import java.io.IOException;

/**
 * A command for the command "mark".
 * Serves to mark and unmark tasks as done.
 */
public class MarkCommand extends Command {
    private final int num;
    private final boolean isMarking;
    public MarkCommand(int num, boolean isMarking) {
        this.num = num;
        this.isMarking = isMarking;
    }

    /**
     * Marks and unmarks a task in the task list.
     * This task is identified by its number.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String message;
        if (isMarking) {
            tasks.markTask(num);
            message = String.format(
                    "Beep boop! I've marked this task as done:\n %s", tasks.get(num));
            ui.display(message);
        } else {
            tasks.unmarkTask(num);
            message = String.format(
                    "Boop beep! I've unmarked this task as done:\n %s", tasks.get(num));
            ui.display(message);
        }

        try {
            storage.dumpFile(tasks);
        } catch (Exception err) {
            throw new DukeException("Error while saving file!");
        }

        return message;
    }
}
