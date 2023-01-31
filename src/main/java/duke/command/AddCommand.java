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
    private boolean exit;

    /**
     * Initialises add class
     *
     * @param instruction command of the task
     * @param input       name of the task
     */
    public AddCommand(String instruction, String input) {
        this.instruction = instruction;
        this.input = input;
        this.exit = false;
    }

    /**
     * Exits duke if it detects bye command
     *
     * @return boolean false
     */
    @Override
    public boolean isExit() {
        return this.exit;
    }

    /**
     * Adds task into task list
     *
     * @param taskList arraylist that stores tasks
     * @param storage  stores data of tasks
     * @param ui       responds to user input
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.addItem(instruction.toString(), input);
        return "Task has been added";
    }
}
