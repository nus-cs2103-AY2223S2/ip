package duke.command;

import duke.DukeExceptions;
import duke.TodoList;

/**
 * Represents an add command.
 */
public class AddTaskCommand extends Command{
    private TodoList todoList;
    private String instruction;
    private String description;

    public AddTaskCommand(TodoList todoList, String instruction, String description) {
        this.todoList = todoList;
        this.instruction = instruction;
        this.description = description;
    }

    @Override
    public String execute() throws DukeExceptions {
        String result = todoList.add(instruction, description);
        return(String.format("%s\nNow I have %d tasks in the list.", result, todoList.number_of_tasks()));
    }
}
