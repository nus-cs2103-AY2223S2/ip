package duke;

/**
 * A command for event tasks to be added.
 */
public class EventCommand extends Command {

    /**
     * Constructs the event command with given user input.
     *
     * @param input Input from user.
     */
    public EventCommand(String input) {
        super(input);
    }

    /**
     * Executes the event command with given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If task description is empty.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.getInput().length() == 0) {
            throw new DukeException(
                    "You can't be doing nothing!! Please try again!");
        }

        String[] descSplit = this.getInput().split("/");
        String description = descSplit[0].substring(
                0, descSplit[0].length() - 1);
        String start = descSplit[1].substring(
                5, descSplit[1].length() - 1);
        String end = descSplit[2].substring(3);
        Event taskEvent = new Event(description, start, end);

        taskList.addTask(taskEvent);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskEvent);
        ui.showNumTasks(taskList);
    }

    /**
     * Executes the Event command with given task list,
     * ui and storage, and also returns output String for bot.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @return Formatted output message.
     * @throws DukeException If error occurs.
     */
    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (this.getInput().length() == 0) {
            throw new DukeException(
                    "You can't be doing nothing!! Please try again!");
        }

        String[] descSplit = this.getInput().split("/");
        String description = descSplit[0].substring(
                0, descSplit[0].length() - 1);
        String start = descSplit[1].substring(
                5, descSplit[1].length() - 1);
        String end = descSplit[2].substring(3);
        Event taskEvent = new Event(description, start, end);

        taskList.addTask(taskEvent);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        return ui.formatTaskAdded(taskEvent, taskList);
    }
}
