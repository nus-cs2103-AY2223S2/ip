package command;

import storage.TaskList;
import task.Todo;

/**
 * Command component that executes an todo command.
 */
public class TodoCommand extends Command {
    private String task;

    /**
     * Constructor for a create todo command.
     *
     * @param task task to be added
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    @Override
    public String run(TaskList taskList) {
        assert this.task.trim() != "";
        Todo newTodo = taskList.createTodo(this.task);
        return "Got it. I've added this task:\n" + newTodo + "\nNow you have " + taskList.countTask()
                + " tasks in the list.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TodoCommand)) {
            return false;
        }

        TodoCommand that = (TodoCommand) o;

        return task.equals(that.task);
    }
}
