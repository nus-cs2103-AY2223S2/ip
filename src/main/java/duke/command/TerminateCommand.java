package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * TerminateCommand that represents command to end the program.
 */
public class TerminateCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Constructor for TerminateCommand.
     */
    public TerminateCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTasks(tasks.getArrayList());
            ui.showGoodBye();
            this.responseFromDukeAfterExecution = "Bye. Hope to see you again soon!";
        } catch (DukeException e) {
            Ui.showResponse("Error in saving tasks");
            this.responseFromDukeAfterExecution = "Error in saving tasks";
        }
    }

    public boolean isExit() {
        return true;
    }
}
