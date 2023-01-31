package duke.commands;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

public class ByeCmd extends Command {

    public ByeCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public void execute() throws CommandExecutionError {
        Ui.shutDown();
        try {
            Storage.saveToFile(taskList);
        } catch (IOException e) {
            // Ui.showSavingError()
        }
    };

    // Acknowlege on UI that Duke is exiting.
    public void uiReply() {
        Ui.displayMsg("Bye. Hope to see you again soon!");
    };
}
