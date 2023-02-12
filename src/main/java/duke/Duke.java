package duke;

import duke.command.Command;
import duke.enums.Languages;
import duke.enums.Views;

/**
 * Main class that runs the chatbot
 */
public class Duke {

    private static Languages language = Languages.ENG;
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
            Ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Static variable to get the language of Duke
     *
     * @return
     */
    public static Languages getLang() {
        return language;
    }

    /**
     * Static variable to set the language of Duke
     *
     * @return
     */
    public static void setLang(Languages input) {
        language = input;
    }

    /**
     * Runs the chatbot
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                if (e instanceof DukeException) {
                    Ui.showError(e);
                } else {
                    Ui.showError(Views.UNKNOWN_ERR_STRING.str());
                }
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
        return c.executeString(tasks, storage);
    }
}
