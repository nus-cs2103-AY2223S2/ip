package duke.commands;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Todo;


/**
 * > This class is a command that adds a todo to the task list
 */
public class AddTodo extends Command{
    // A variable that stores the description of the task.
    private String description;

    // This is the constructor of the class. It takes in a string as input and checks if the length of
    // the string is less than 5. If it is, it throws an exception. If not, it assigns the description
    // of the task to the string.
    public AddTodo(String input) {
        try {
            if (input.length() < 5) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty");
            }
            this.description = input.substring(5);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * The function takes in a task list, a storage, and a ui, and then creates a new task object with
     * the description of the command, adds it to the task list, saves the task list, and prints the
     * added task
     *  @param tasks the list of tasks
     * @param ui the user interface
     * @param storage Storage
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Todo t = new Todo(this.description);
        tasks.add(t);
        storage.saveTaskList(tasks);
        return ui.printAddedTask(t, tasks.size());

    }
}
