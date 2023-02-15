package commands;

import features.DukeException;
import features.Trivia;
import features.Ui;

/**
 * Handles 'fact' command.
 */
public class CommandFact extends Command {
    /**
     * Returns a cool fact.
     * @param userInput The user's String input in array form. Redundant.
     * @return The cool fact message.
     * @throws DukeException Thrown if an error occurs. Redundant.
     */
    @Override
    public String handle(String[] userInput) throws DukeException {
        Ui ui = new Ui();
        return (ui.formatTrivia(new Trivia().getFact()));
    }
}
