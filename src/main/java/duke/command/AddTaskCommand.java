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
    public void execute() throws DukeExceptions {
        todoList.add(instruction, description);
        System.out.println(String.format("Now I have %d tasks in the list.", todoList.number_of_tasks()));
    }
}
