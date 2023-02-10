package duke;

import java.io.IOException;

/**
 * Represents Main class
 */
public class Duke {

    private final Storage storage;
    private TaskList tasks;

    /**
     * The construction of duke
     */
    public Duke(String filePath) {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets a response to the user command
     * @param input user command
     * @return response to the user command
     */
    public String getResponse(String input) {
        String temp = "";

        try {
            if (input.equals("bye")) {
                temp += Ui.exit();
            } else {
                temp = Parser.parse(input, tasks);
                storage.updateFile(tasks);
            }
            return temp;
        } catch (IOException e) {
            return "Error occurs!";
        }
    }
}
