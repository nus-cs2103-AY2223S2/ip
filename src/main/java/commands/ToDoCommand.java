package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

import tasks.Todo;

import java.time.format.DateTimeParseException;

public class ToDoCommand implements Command {
    private String input;

    public ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Adds a new todo task to the list of tasks.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IllegalArgumentException {
        assert !input.isEmpty() : "Input to ToDo Command can't be empty";
        try {
            Todo todo = new Todo(getDescription());
            taskList.getTaskList().add(todo);
            storage.store(taskList);
            return ui.newToDoMessage(todo);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the description of the todo task.
     *
     * @return The description of the todo task.
     */
    public String getDescription() {
        return input.split(" ", 2)[1];
    }
}
