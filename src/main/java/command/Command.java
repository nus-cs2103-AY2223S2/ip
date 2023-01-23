package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.*;

/***
 * Serves as a blueprint for the other commands to follow
 */
public abstract class Command{

    private boolean isExit;

    /**
     * Constructor that initializes whether the program should exit or not
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * return isExit status
     * @return boolean
     */
    public boolean isExit() {
        return this.isExit;
    }

    /***
     * Sets isExit as true
     */
    public void setExitTrue(){
        isExit = true;
    }

    /**
     * abstract method to execute a command
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
