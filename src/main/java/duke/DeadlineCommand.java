package duke;

/**
 * A command for deadline tasks to be added.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs the deadline command with given user input.
     *
     * @param input Input from user.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    /**
     * Executes the deadline command with given task list,
     * ui and storage.
     *
     * @param taskList TaskList for Duke.
     * @param ui Ui for Duke.
     * @param storage Storage for Duke.
     * @throws DukeException If task description is empty.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException(
                    "You cant be doing nothing!! Please try again!");
        }

        // String desc = input.substring(9);
        String[] descSplit = input.split("/");
        Deadline taskDeadline = new Deadline(
                descSplit[0].substring(0,descSplit[0].length()-1),
                descSplit[1].substring(3));

        taskList.addTask(taskDeadline);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskDeadline);
        ui.showNumTasks(taskList);
    }
}
