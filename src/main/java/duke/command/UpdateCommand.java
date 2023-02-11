package duke.command;

import duke.TaskList;
import duke.Ui;

public class UpdateCommand extends Command {
    protected int index;

    public UpdateCommand(String commandName, int index) {
        super(commandName);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if ((this.index) > tasks.size()) {
            ui.showIdExceedsList(this.index);
        } else {
            switch (this.commandName) {
            case "mark": {
                tasks.get(this.index - 1).setDone();
                ui.markTask(tasks, this.index - 1);
                break;
            }
            case "unmark": {
                tasks.get(this.index - 1).setNotDone();
                ui.unmarkTask(tasks, this.index - 1);
                break;
            }
            case "delete": {
                ui.deleteTask(tasks, this.index - 1);
                tasks.remove(this.index - 1);
                break;
            }
            default: {
                break;
            }
            }
        }
    }
}
