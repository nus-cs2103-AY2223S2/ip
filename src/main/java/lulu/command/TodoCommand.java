package lulu.command;

import lulu.TaskList;
import lulu.Ui;
import lulu.Storage;

import lulu.exception.LuluException;

import lulu.task.Task;
import lulu.task.Todo;

/**
 * This command is used to create a new ToDo task. When an invalid format has been used by the user,
 * the class throws exceptions to remind the user of the valid format.
 * When executed, a new ToDo task is added to TaskList.
 * It has an additional description attribute for the ToDo task description.
 */
public class TodoCommand extends Command {
    private String description;

    public TodoCommand(String rest) throws LuluException {
        if (rest.isEmpty()) {
            throw new LuluException("(=✖ ᆺ ✖=) The description of a todo cannot be empty.");
        }
        this.description = rest;
    }

    /**
     * This method adds a todo task to tasks upon execution.
     *
     * @param tasks   the TaskList to be added with a todo task
     * @param ui      the UI that displays messages
     * @param storage the Storage is not relevant in this command
     * @return a String that displays the ToDo task added
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        return ui.showContainer(ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize()));
    }
}