package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

public class ListCommand extends Command{
    private TodoList todoList;

    public ListCommand(TodoList todoList) {
        this.todoList = todoList;
    }

    @Override
    public String execute() throws DukeExceptions {
        String shown_list = todoList.toString();
        return(shown_list);
    }
}

