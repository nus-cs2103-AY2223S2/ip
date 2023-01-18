package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ThroughCommand extends Command {
    private String input;

    public ThroughCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList taskList, Ui inter, Storage store) {
        inter.through(input, taskList);
    }

    public boolean isExit() {
        return false;
    }
}
