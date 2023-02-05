package duke.ui;

import duke.command.Command;
import duke.command.Parser;
import duke.exceptions.DukeException;
import duke.task.TaskList;


/**
 * Class that handles the interface that user interacts with.
 */
public class UI {
    private Parser parser;

    /**
     * Constructor for UI object.
     * @param tasks A list of possible saved tasks from the previous session.
     */
    public UI(TaskList tasks) {
        this.parser = new Parser(tasks);
    }

    /**
     * Processes user input.
     * @return a string that to be displayed to the user in response to the input.
     * @throws DukeException Throws DukeException if an invalid input is encountered.
     */
    public String processInput(String input) throws DukeException {
        // get user input and trim trailing white sp
        String toProcess = input.trim();
        String firstWord = toProcess.split(" ")[0];
        Command command = Command.get(firstWord);
        String response = this.parser.execute(command, toProcess);
        return response;
    }
}
