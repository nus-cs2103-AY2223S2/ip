package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class EchoCommand extends Command {
    private final String input;
    public EchoCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // do the commands purpose
        // ui.print() the correct output
        ui.print(input);
    }
}
