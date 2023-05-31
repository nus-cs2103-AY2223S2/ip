package duke;

/**
 * A command to find tasks by searching.
 */
public class FindCommand extends Command {

    /**
     * Constructs the find command with given user input.
     *
     * @param input Input from user.
     */
    public FindCommand(String input) {
        super(input);
    }

    /**
     * Executes the find command with given task list
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            ui.displayFormattedMessage("Task list is empty, can't find anything!");
        } else {
            ui.showMatchingTasksMessage();
            int matchingTasksCounter = 0;
            for (int i = 1; i <= taskList.getSize(); i++) {
                Task curTask = taskList.getTask(i);
                if (curTask.getDescription().contains(this.getInput())) {
                    matchingTasksCounter += 1;
                    ui.displayFoundTask(matchingTasksCounter, curTask);
                }
            }
        }
    }

    /**
     * Executes the Find command with given task list,
     * ui and storage, and also returns output String for bot.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @return Formatted output message.
     * @throws DukeException If error occurs.
     */
    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.getSize() == 0) {
            return ui.formatMessage("Task list is empty, can't find anything!");
        } else {
            String matchingTasks = "Matching tasks are shown below: ";
            int matchingTasksCounter = 0;
            for (int i = 1; i <= taskList.getSize(); i++) {
                Task curTask = taskList.getTask(i);
                if (curTask.getDescription().contains(this.getInput())) {
                    matchingTasksCounter += 1;
                    matchingTasks = matchingTasks + "\n" + ui.formatFoundTask(matchingTasksCounter, curTask);
                }
            }
            return matchingTasks;
        }
    }
}
