package command;

import command.Command;
import duke.DukeException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkTaskCommand extends Command {
    private int idx;
    public MarkTaskCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(idx - 1);
        ui.formResponse("Task masked as done: " + tasks.getTask(idx - 1));
        storage.save(tasks.getList());
    }

}
