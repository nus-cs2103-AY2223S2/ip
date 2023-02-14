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

    public String execute() throws CommandExecutionError {
        Ui.shutDown();
        try {
            Storage.saveToFile(taskList);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Bye. Hope to see you again soon!";
    }
}
