package duke;
import java.time.format.DateTimeParseException;

import duke.commands.Command;

/**
 * The main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor.
     */
    public Duke() {
        String filePath = "tasks.txt";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTaskList();
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Shows welcome message on start.
     *
     * @return String The welcome message.
     */
    public String showWelcomeMessage() {
        return this.ui.showWelcomeMessage();
    }


    /**
     * Generate response based on user input.
     *
     * @param input The user input.
     * @return String of the response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.ui, this.storage);
        } catch (IllegalArgumentException e) {
            return "Unrecognised command. Try again.";
        } catch (DateTimeParseException e) {
            return "Key in date and time in this format. yyyy-mm-ddThh:mm";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
