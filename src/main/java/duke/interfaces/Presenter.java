package duke.interfaces;

import duke.exceptions.CommandException;
import duke.exceptions.PresenterException;

/**
 * Presenter is an interface for the presenter component application.
 * It acts as a mediator between the model and the view.
 */
public interface Presenter {
    /**
     * Handles a user-issued command.
     *
     * @param string the input to handle
     */
    void handleInput(String string) throws PresenterException, CommandException;
}
