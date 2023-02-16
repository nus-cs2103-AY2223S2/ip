package duke;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Represents the chatbot users interact with.
 */
public class Duke {

    private static final String FILE_DIRECTORY = "data";
    private static final String FILE_NAME = "duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String name;

    /**
     * Constructor for the bot.
     */
    public Duke() {
        this.name = "LeDuke";
        this.ui = new Ui(name);
        this.storage = new Storage(FILE_DIRECTORY, FILE_NAME, ui);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Forms a response from the user input.
     * Terminates the bot if command is BYE.
     *
     * @param input String representing an input.
     * @return String representing Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            assert c != null : "Command cannot be null";

            String response = c.execute(tasks, ui, storage);
            assert response != null : "Response cannot be null";

            return response;
        } catch (DukeException e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Returns the name of the bot
     * @return String as the name of the bot.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a welcome message from the bot.
     * @return String as a welcome message .
     */
    public String createWelcomeMessage() {
        return ui.sayHello();
    }
}
