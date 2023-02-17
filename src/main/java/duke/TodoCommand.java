package duke;

/**
 * A command for Todo tasks to be added.
 */
public class TodoCommand extends Command {

    /**
     * Constructs the todo command with given user input.
     *
     * @param input Input from user.
     */
    public TodoCommand(String input) {
        super(input);
    }

    /**
     * Executes the todo command with given task list,
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
        Todo taskTodo = new Todo(input);
        taskList.addTask(taskTodo);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        ui.showTaskAdded(taskTodo);
        ui.showNumTasks(taskList);
    }

    public String executeReturnString(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException(
                    "You cant be doing nothing!! Please try again!");
        }
        Todo taskTodo = new Todo(input);
        taskList.addTask(taskTodo);
        storage.writeTasksToFile(taskList.getTaskList().toString());
        return ui.formatTaskAdded(taskTodo, taskList);
    }

}
