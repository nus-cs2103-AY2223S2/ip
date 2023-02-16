package duke.command;

import duke.TaskList;
import duke.Ui;

public class Bye extends Command {

    public Bye(String input) {
        super(input);
    }

    /**
     * Ends the parser.
     *
     * @param tasks The current Task List.
     * @return The current Task List.
     */
    public TaskList execute(TaskList tasks) {
        return tasks;
    }
}
