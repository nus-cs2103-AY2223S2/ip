package duke;

import duke.command.Command;
import duke.task.*;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Main Chatbot logic
 */
public class Duke {

    public static String LOGO = "______     ______     __     __    \n" +
            "/\\  __ \\   /\\  == \\   /\\ \\   /\\ \\   \n" +
            "\\ \\  __ \\  \\ \\  __<   \\ \\ \\  \\ \\ \\  \n" +
            " \\ \\_\\ \\_\\  \\ \\_\\ \\_\\  \\ \\_\\  \\ \\_\\ \n" +
            "  \\/_/\\/_/   \\/_/ /_/   \\/_/   \\/_/ \n";

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Duke chatbot.
     * @param filePath Path for data storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        taskList = tasks;

        parser = new Parser(taskList, storage, ui);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String cmd) {
        try {
            Command command = parser.parseUserCommand(cmd);
            return command.execute();
        } catch (DukeException e) {
            return "Sorry, I ran into a problem... Try again";
        }
    }

}
