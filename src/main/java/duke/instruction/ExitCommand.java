package duke.instruction;

import duke.task.TaskList;

/**
 * An ExitCommand class that encapsulates the event of terminating the Duke program.
 */

public class ExitCommand extends Command {
    /**
     * Displays the goodbye message and terminate the program.
     *
     * @param list The user TaskList that contains all the task to be manipulated
     */
    @Override
    public void run(TaskList list) {
        format.displayWithBar("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
