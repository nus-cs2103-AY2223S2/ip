package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;

import lulu.exception.LuluException;

import lulu.task.Task;
import lulu.task.Todo;

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
     */
    public String execute(TaskList tasks, UI ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        return ui.showContainer(ui.showAddText(tasks.getRecentTaskDescription(), tasks.getSize()));
    }
}