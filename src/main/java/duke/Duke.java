package duke;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;
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
     * Gets the response from the input string.
     *
     * @param input Input string to be parsed.
     * @return The response string.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            response = this.ui.showError(e);
        }
        System.out.println(response);
        return response;
    }

    /**
     * Gets the greeting message when the program is first started.
     *
     * @return The greeting message.
     */
    public String getGreetingMessage() {
        return this.ui.greet();
    }


    /**
    * Creates the localdatetime by parsing the text string.
    *
    * @param dateTime The string representation of the local date time.
    * @return The LocalDateTime object being created.
    */
    public static LocalDateTime createLocalDateTime(String dateTime) {
        assert !dateTime.equals("");
        LocalDateTime date;
        try {
            String stringWithNoTrailingWhitespaces = dateTime.trim();
            date = LocalDateTime.parse(stringWithNoTrailingWhitespaces, FORMATTER);
        } catch (DateTimeException e) {
            date = null;
        }
        return date;
    }


    /**
     * Returns the formatter for date time.
     *
     * @return The date time formatter used in duke.
     */
    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }




}



