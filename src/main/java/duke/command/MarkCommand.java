package duke.command;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.isMarked) {
            tasks.get(this.idx).markAsDone();
            ui.printInBanner("Yatta! You have done this task!", tasks.get(this.idx).toString());
        } else {
            tasks.get(this.idx).unmarkAsDone();
            ui.printInBanner("Neee! Are you kidding me?", tasks.get(this.idx).toString());
        }

        storage.saveTasklistToFile(tasks);
    }
}
