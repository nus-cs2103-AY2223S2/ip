package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    private static final String DEFAULT_MSG = "Hmm... I can't quite understand you :-/";
    private String msg;

    /**
     * A constructor to initialize the message of this invalid command.
     * @param result The message of this invalid command.
     */
    public InvalidCommand(String result) {
        msg = result;
    }

    public InvalidCommand() {

    }
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        if (msg != null) {
            return msg;
        } else {
            return DEFAULT_MSG;
        }
    }
}
