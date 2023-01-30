package duke;

import java.io.File;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.commands.Command;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * The Duke class.
 */
public class Duke {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * * Duke constructor.
     *
     * @param filePathName The string representing the path to the file duke.
     */
    public Duke(String filePathName) {
        TaskList tasks1;
        this.ui = new Ui();
        this.storage = new Storage(filePathName);
        try {
            tasks1 = new TaskList(this.storage.loadFromFile());
        } catch (DukeException e) {
            tasks1 = new TaskList(new ArrayList<>());
        }
        this.tasks = tasks1;
    }

    /**
    * Creates the localdatetime by parsing the text string.
    *
    * @param dateTime The string representation of the local date time.
    * @return The LocalDateTime object being created.
    */
    public static LocalDateTime createLocalDateTime(String dateTime) {
        LocalDateTime date;
        try {
            String stringWithNoTrailingWhitespaces = dateTime.trim();
            date = LocalDateTime.parse(stringWithNoTrailingWhitespaces, FORMATTER);
            // DateTimeFormatter.ofPattern("d MMM uuuu h.mma")
        } catch (DateTimeException e) {
            date = null;
        }
        return date;
    }

    /**
     * Runs duke.
     */
    public void run() {
        this.ui.greet();
        while (true) {
            try {
                String fullCommand = this.ui.readCommand();
                this.ui.showLine();
                Command c = Parser.parse(fullCommand);
                if (c.isExit()) {
                    this.ui.sayGoodBye();
                    break;
                }
                c.execute(this.tasks, this.ui, this.storage);
            } catch (DukeException e) {
                this.ui.showError(e);
            } finally {
                this.ui.showLine();
            }
        }
        System.exit(0);
    }

    /**
     * Function that returns the formatter for date time.
     *
     * @return The date time formatter used in duke.
     */
    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }

    /**
     * The main function.
     * @param args Inputs into the main function.
     */
    public static void main(String[] args) {
        String filePathName = "." + File.separator + "src" + File.separator
                + "main" + File.separator + "data" + File.separator + "duke";
        new Duke(filePathName).run();
    }
}

