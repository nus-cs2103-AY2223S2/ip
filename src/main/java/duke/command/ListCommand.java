package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printTasks(tasks);
    }
}
