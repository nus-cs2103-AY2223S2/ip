package duke.commands;

import duke.TaskList;

/**
 * This class represents the command to exit the program.
 */
public class Exit extends Command {
    private static final String RESPONSE = "Done already? Press the enter key or enter any input to exit.\n"
            + "Get to work!";
    public Exit(String message) {
        super(message);
    }

    /**
     * Does nothing; exits the program.
     *
     * @param toDoList The list at the end of the program.
     */
    @Override
    public void execute(TaskList toDoList) {
        // nothing to do; simply exits
    }

    @Override
    public String getResponseOutput() {
        return Exit.RESPONSE;
    }
}
