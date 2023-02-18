package duke.command;

import duke.Storage;
import duke.Task;
import duke.Tasklist;
import duke.Ui;


/**
 * Class representing the add command
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor method for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(Tasklist tasklist, int saveNo) throws Exception {
        tasklist.add(this.task);
        Storage.save(tasklist, saveNo);
        return Ui.showAdd(this.task) + "\n" + Ui.showTasklistSize(tasklist);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
