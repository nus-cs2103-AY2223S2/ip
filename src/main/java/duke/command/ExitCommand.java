package duke.command;

import duke.*;

public class ExitCommand extends Command {
    public ExitCommand(String userInput) {
        super(userInput);

    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
         ui.sayGoodbye();
         isExit = true;
     }
}
