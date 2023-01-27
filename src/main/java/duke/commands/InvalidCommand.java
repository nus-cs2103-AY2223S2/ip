package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;

public class InvalidCommand extends Command {
    private static final String DEFAULT_MSG = "Hmm... I can't quite understand you :-/";
    private String msg;

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
    public void execute(TaskList taskList, Storage storage) {
        if (msg != null) {
            System.out.println(msg);
        } else {
            System.out.println(DEFAULT_MSG);
        }
    }
}
