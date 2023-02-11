package duke;

import duke.command.Command;
import duke.enums.Views;

/**
 * Main class that runs the chatbot
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Default constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                if (e instanceof DukeException) {
                    ui.showError(e);
                } else {
                    ui.showError(Views.UNKNOWN_ERR_STRING.eng());
                }
            } catch (AssertionError e) {
                ui.showError(e);
            }
        }
    }

    /**
     * Main method for running the text Version
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Generate response from Duke to put into UI
     *
     * @param input
     * @return String response from Duke
     * @throws DukeException
     */
    public String getResponse(String input) throws DukeException {
        Command c = Parser.parse(input);
        return c.executeString(tasks, ui, storage);
    }
}
