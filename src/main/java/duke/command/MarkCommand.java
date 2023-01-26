package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(index);
        ui.showMarkSucess(tasks.get(index));
    }
}
