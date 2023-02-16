package duke;
import java.io.IOException;
import java.util.Scanner;

import exception.DukeException;

/**
 * A chatbot that receives user's input on various predetermined
 * command types and performs relevant functions.
 */
public class Duke {
    private static TaskList tasks;
    private Storage storage;
    private Parser parser;
    public boolean isExit;

    /**
     * Initializes a bot with provided path to the storage space.
     *
     * @param filePath Path to the storage file.
     * @param directoryPath Path to the storage directory.
     */
    public Duke(String filePath, String directoryPath) {
        storage = new Storage(filePath, directoryPath);
        tasks = new TaskList();
        parser = new Parser(tasks, storage);
        try {
            tasks = storage.loadTasks();
        } catch (DukeException | IOException e) {
            Ui.print(String.valueOf(e));
        }
    }

    /**
     * Returns response of the bot.
     *
     * @param input User's input.
     * @return Response that corresponds to the input.
     */
    public String getResponse(String input) {
        String res = parser.parse(input);
        isExit = res.equals("Bye bye!");
        return res;
    }

    /**
     * Greets user with a welcome message.
     *
     * @return Welcome message.
     */
    public String getWelcomeMessage() {
        return parser.parse("greet");
    }
}
