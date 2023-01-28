package duke.interfaces;

import duke.exceptions.CommandException;
import duke.exceptions.PresenterException;

public interface Presenter {
    void handleInput(String string) throws PresenterException, CommandException;
}
