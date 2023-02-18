package duke;

public class SortCommand extends Command {
    /**
     * Constructs the list command with given user input.
     *
     * @param input Input from user.
     */
    public SortCommand(String input) {
        super(input);
    }

    /**
     * Executes the list command to display the list,
     * given task list, ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If error occurs.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.sortTasks(taskList, new TaskDateComparator());
        storage.writeTasksToFile(taskList.getTaskList().toString());
    }

    /**
     * Executes the Sort command with given task list,
     * ui and storage, and also returns output String for bot.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @return Formatted output message.
     * @throws DukeException If error occurs.
     */
    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.sortTasks(taskList, new TaskDateComparator());
        storage.writeTasksToFile(taskList.getTaskList().toString());
        return ui.formatShowList(taskList);
    }
}
