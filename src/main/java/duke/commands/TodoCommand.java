package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Todos;

/**
 * A command type that the chatting bot can read.
 */
public class TodoCommand extends Command {

    private String taskName;

    /**
     * Constructs of this class.
     *
     * @param taskName
     */
    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Gets the task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) {
        Todos newTodo = new Todos((taskName));
        if (list.isExist(newTodo)) {
            return "Ah, the task is already in the list.";
        }
        list.add(newTodo);
        store.save(list);
        String response = "Okay. I've added this task:\n";
        response += list.get(list.size() - 1).toString();
        response += "\nNow you have " + list.size() + " tasks in the list.";
        return response;
    }

    /**
     * Checks if the programme should exit.
     *
     * @return a boolean value stating the bot should not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
