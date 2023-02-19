package duke;

import duke.command.Command;


/**
 * Instance of Duke class.
 */
public class Duke {
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    /**
     * Creates an instance of the Duke chatbot.
     * @param filePath File path to store task data for Tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts an instance of the Duke chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                ui.reply(c.execute(tasks, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.reply(e.toString());
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                System.exit(0);
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public static void main(String[] args) {
        new Duke("/duke.txt").run();
    }
}
