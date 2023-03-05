package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

public class MarkCommand extends Command{
    private TodoList todoList;
    private int index;

    public MarkCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeExceptions { return todoList.mark(index); }
}
