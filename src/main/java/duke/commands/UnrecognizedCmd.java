package duke.commands;

import duke.Ui;
import duke.exceptions.CommandExecutionError;
import duke.tasks.TaskList;

public class UnrecognizedCmd extends Command {
    public UnrecognizedCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    public void execute() throws CommandExecutionError {
        uiReply();
    };

    // Acknowlege on UI that command is not recognized.
    public void uiReply() {
        Ui.displayMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
    };
}
