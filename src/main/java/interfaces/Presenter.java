package interfaces;

import exceptions.CommandException;
import exceptions.PresenterException;

public interface Presenter {
    void handleInput(String string) throws PresenterException, CommandException;
}
