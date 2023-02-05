package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Gives command to add item
 */
public class AddCommand extends Command {
    private String instruction;
    private String input;

    /**
     * Initialises add class
     *
     * @param instruction command of the task
     * @param input       name of the task
     */
    public AddCommand(String instruction, String input) {
        this.instruction = instruction;
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addItem(instruction.toString(), input);
        return "Task has been added";
    }
}
