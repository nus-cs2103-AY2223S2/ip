package duke.command;

import duke.*;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printTasks(tasks);
    }
}
