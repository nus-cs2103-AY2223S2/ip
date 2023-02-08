package duke.command;

import duke.tasklist.TaskList;

/**
 * Represents the class of command which finds tasks from the taskList.
 */
public class FindCommand {
    private TaskList tasks;

    public FindCommand(TaskList arrList) {
        this.tasks = arrList;
    }

    /**
     * Logic for the finding the task.
     * Returns a String representation of the text that is generated
     * upon running the specific find command.
     *
     * @param input String representation of what to find.
     * @return String that represents the program output.
     */
    public String execute(String input) {
        TaskList toPrint = this.tasks.search(input);
        String toReturn = "Here are the matching tasks in your list:" + toPrint.printList();
        return toReturn;
    }
}
