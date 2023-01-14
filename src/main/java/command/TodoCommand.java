package command;

import storage.TaskList;
import task.Todo;

public class TodoCommand extends Command {
    private String task;

    /**
     * Constructor for a create todo command.
     * @param task  task to be added
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    @Override
    public String run(TaskList taskList) {
        Todo newTodo = taskList.createTodo(this.task);
        return "Got it. I've added this task:\n" + newTodo +
                "\nNow you have " + taskList.countTask() + " tasks in the list.";
    }
}
