package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

/**
 * Represents find command.
 */
public class FindCommand extends Command{
    private TodoList todoList;
    private String description;

    public FindCommand(TodoList todoList, String description) {
        this.todoList = todoList;
        this.description = description;
    }

    @Override
    public void execute() throws DukeExceptions {
        todoList.find(description);
    }
}
