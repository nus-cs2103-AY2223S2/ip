package duke.command;

import duke.*;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private int taskNum;

    MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum - 1 < 0 || taskNum - 1>= tasks.size()) {
            throw new DukeException("\u2639 OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        tasks.markAsDone(taskNum);
        Ui.showMessage("Nice! I've marked this task as done:\n\t" + tasks.get(taskNum));
        storage.save(tasks.getAllTasks());
    }
}