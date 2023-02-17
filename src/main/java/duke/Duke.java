package duke;

import duke.commands.Command;

/**
 * Main driver class for Duke
 */
public class Duke {
    public static final String TERMINATION_INDICATION = "TERMINATE";
    private TaskList tasks;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor to initialize required components
     *
     * @throws DukeException If failed to recover list of tasks
     */
    public Duke() throws DukeException {
        storage = new Storage();
        tasks = storage.recoverList();
        parser = new Parser();
    }

    /**
     * Executes commands given user input
     *
     * @param input User input
     * @return String of result from command execution
     * @throws DukeException If command is invalid or there is storage saving issues
     */
    public String executeCommand(String input) throws DukeException {
        Command command = parser.parse(input);
        if (command == Parser.EXIT_COMMAND) {
            return TERMINATION_INDICATION;
        }
        String result = command.execute(tasks);
        storage.saveTaskChangesToFile(tasks);
        return result;
    }
}
