package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

public class UnmarkCommand extends Command{
    private TodoList todoList;
    private int index;

    public UnmarkCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeExceptions {
        return todoList.unmark(index);
    }
}
