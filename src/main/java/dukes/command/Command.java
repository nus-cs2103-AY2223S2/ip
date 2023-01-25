package dukes.command;

import dukes.util.DukeException;
import dukes.util.TaskList;
import dukes.util.UI;
import dukes.util.Storage;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class that wraps the information of interpreted command.
 * Pass the information for execution to TaskList, UI and Storage.
 */
public abstract class Command {
    protected boolean isExit;
    protected boolean isValid;
    protected String header;
    protected String body;

    /**
     * Constructor of Command class.
     *
     * @param isExit show if the command is an ExitCommand.
     * @param isValid show if the command is valid.
     * @param header specify the type the command belongs to, e.g. "add", "delete".
     * @param body specify the key information the command supposed to pass on.
     */
    public Command(boolean isExit, boolean isValid, String header, String body) {
        // Abstract class CAN have constructors, but only instantiate in sub-class
        this.isExit = isExit;
        this.isValid = isValid;
        this.header = header;
        this.body = body;
    }

    /**
     * Abstract method that passes the wrapped information for execution to TaskList, UI and Storage.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if unexpected runtime issue occurs.
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws DukeException;
    // static method cannot be abstract
    // if child method wants to throw exception, parent method must also do

    public boolean isExit() {
        return this.isExit;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
