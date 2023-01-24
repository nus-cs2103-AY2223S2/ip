package dukes.command;

import dukes.util.*;
import dukes.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Subclass of Command that handles the command to exit Duke.
 */
public class ExitCommand extends Command {

    /**
     * Constructor of ExitCommand class.
     *
     * @param isExit show if the command is an ExitCommand. True for all ExitCommand.
     * @param isValid show if the command is valid.
     * @param header the type the command belongs to, e.g. "add", "delete".
     * @param body nothing.
     */
    public ExitCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        super(isExit, isValid, header, body);
    }

    /**
     * Expect to do nothing, just break the while cycle.
     *
     * @param tasks contains the task list.
     * @param ui the UI in charge of user interactions.
     * @param storage handles the loading and saving of files.
     * @throws DukeException if the index provided is out of bounds.
     */
    public void execute(TaskList tasks, UI ui, Storage storage) {
        // do nothing
    }

}
