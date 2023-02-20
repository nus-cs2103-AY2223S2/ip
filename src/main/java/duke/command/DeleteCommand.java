package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

public class DeleteCommand extends Command{
    private TodoList todoList;
    private int index;

    public DeleteCommand(TodoList todoList, int index) {
        this.todoList = todoList;
        this.index = index;
    }

    @Override
    public String execute() throws DukeExceptions {
        String message = todoList.delete(index);
        return(String.format("%s\nNow I have %d tasks in the list.", message, todoList.getNumberOfTasks()));
    }
}
