package commands;

import features.DukeException;

/**
 * Handles 'bye' command.
 */
public class CommandBye extends Command {
    /**
     * Returns a goodbye message.
     * @param userInput The user's String input in array form. Redundant.
     * @return The goodbye message.
     * @throws DukeException Thrown if an error occurs. Redundant.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        return ("Goodbye, then!");
    }
}
