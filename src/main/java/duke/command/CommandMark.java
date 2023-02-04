package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class CommandMark extends Command {
    private int idx;
    private boolean isDone;
    public CommandMark(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(idx - 1, isDone);
        storage.save(tasks.getList());
        return ui.formResponse("Task marked as " + (isDone ? "done: " : "undone: ") + tasks.getTask(idx - 1));
    }
}
