package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) {
        isExit = true;
        return "Bye. Hope to see you again soon!";

     }
}
