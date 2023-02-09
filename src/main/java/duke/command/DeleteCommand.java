package duke.command;

import duke.tasklist.Task;
import duke.tasklist.TaskList;

/**
 * Represents the class of command which deletes the tasks from the taskList.
 */
public class DeleteCommand {

    private TaskList tasks;

    public DeleteCommand(TaskList arrList) {
        this.tasks = arrList;
    }

    /**
     * Logic for the deletion of the task.
     * Returns a String representation of the text that is generated
     * upon running the specific delete command.
     *
     * @param input String representation of what to delete.
     * @return String that represents the program output.
     */
    public String execute(String input) {
        int index = Integer.parseInt(input);
        Task toDelete = this.tasks.delete(index);
        String toReturn = "Noted. I've removed this task:";
        toReturn = toReturn.concat(toDelete.toString());
        toReturn = toReturn + System.lineSeparator() +
                "Now you have " + this.tasks.size() + " tasks in the list.";
        return toReturn;
    }
}
