package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which updates the task list.
 * This includes command "mark", "unmark" and "delete".
 */
public class UpdateCommand extends Command {
    protected int index;

    /**
     * Class Constructor.
     * @param commandName Input command name.
     * @param index Index of the target task in task list.
     */
    public UpdateCommand(String commandName, int index) {
        super(commandName);
        this.index = index;
    }

    /**
     * Executes the command to update target task in task list.
     * @param tasks Current task list.
     * @param ui Ui to show task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        if ((this.index) > tasks.size()) {
            return ui.showIdExceedsList(this.index);
        } else {
            switch (this.commandName) {
            case "mark": {
                tasks.get(this.index - 1).setDone();
                return ui.markTask(tasks, this.index - 1);
            }
            case "unmark": {
                tasks.get(this.index - 1).setNotDone();
                return ui.unmarkTask(tasks, this.index - 1);
            }
            case "delete": {
                String out = ui.deleteTask(tasks, this.index - 1);
                tasks.remove(this.index - 1);
                return out;
            }
            default: {
                return "";
            }
            }
        }
    }
}
