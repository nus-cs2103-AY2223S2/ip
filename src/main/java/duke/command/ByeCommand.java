package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command{
    private String input;

    /**
     * Constructor for command "Bye".
     *
     * @param input The user's input.
     */
    public ByeCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewellMessage();
    };

    /**
     * Overrides the parent Command class and returns true.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
