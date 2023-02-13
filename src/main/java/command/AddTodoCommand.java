package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Todo;
import userinteraction.Ui;

/**
 * Command class for adding todo tasks.
 */
public class AddTodoCommand extends AddTaskCommand {

    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */
    public AddTodoCommand(String input) {
        super(input);
    }

    /**
     * Creates a todo task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Todo todo = Todo.generate(this.getInput());
        taskList.addTask(todo);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, todo);
    }
}