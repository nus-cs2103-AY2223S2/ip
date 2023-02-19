package duke.command;


import duke.storage.TaskList;
import duke.ui.Ui;
public class InvalidCommand extends Command {
    String message = "";

    public InvalidCommand(String msg) {
        this.message = msg;
    }

    public InvalidCommand() {}

    @Override
    public String execute(TaskList list, Ui ui) {
        if (message.equals("")) {
            return ui.printInvalidCommandMessage();
        } else {
            return message;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
