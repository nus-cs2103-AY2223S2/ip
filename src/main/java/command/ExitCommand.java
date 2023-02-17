package command;

import duke.DukeException;

import task.TaskList;

/**
 * Command for exiting from the program
 */
public class ExitCommand extends Command {
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String BYE_STRING = "bye";
    private static int NUM_COMPONENTS = 1;

    /**
     * Default constructor
     * @param command the user-input command
     * @param doesPrint whether to print messages
     * @throws DukeException when parsing errors arise
     */
    public ExitCommand(String command, boolean doesPrint) throws DukeException {
        super(command, doesPrint, NUM_COMPONENTS);
        isExit = true;
    }

    /**
     * Checks if the given string indicates end of program
     * @param string user-input string
     * @return a boolean value
     */
    public static boolean isByeString(String string) {
        return string.equalsIgnoreCase(BYE_STRING); // hardcode, not ideal
    }

    /**
     * Execute the command
     * @param taskList the list of tasks
     */
    @Override
    public String execute(TaskList taskList) {
        return EXIT_MESSAGE;
    }
}
