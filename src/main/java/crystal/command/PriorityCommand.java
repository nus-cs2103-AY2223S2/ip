package crystal.command;

import crystal.Storage;
import crystal.TaskList;
import crystal.Ui;

/**
 * Represents the priority command when the user enter "priority"
 *
 */
public class PriorityCommand extends Command {

    private int itemNum;
    private int lvlNum;

    /**
     * Constructor for prioritycommand class
     * @param itemNum the item number in the list
     * @param lvlNum the priority level to be given
     */
    public PriorityCommand(int itemNum, int lvlNum) {
        this.itemNum = itemNum;
        this.lvlNum = lvlNum;
    }

    /**
     * Executes the priority command to print the event message.
     *
     * @param tasks tasklist.
     * @param ui ui.
     * @param storage storage.
     *
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveFile(tasks);
        if (this.lvlNum > 9) {
            return "Priority Level cannot be more than 9!";
        } else {
            return ui.printPriority(tasks, this.itemNum, this.lvlNum);
        }
    }

    /**
     * Sets the exit condition to false to continue the program.
     *
     */
    public boolean isExit() {
        return false;
    }
}
