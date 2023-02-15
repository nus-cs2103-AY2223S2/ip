package duke;

import duke.commands.Command;
import duke.exceptions.*;

/**
 * Represents the Duke chatbot.
 * @author lukkesreysandeur
 */
public class Duke {
    /** The Ui object that handles interactions with the user. */
    private final Ui ui;
    /** The list that contains the actual task objects. */
    private final TaskList tasks;
    /** The storage object that helps to save state to a file. */
    private final Storage storage;
    /** The parser object that helps to make sense of the commands entered by the user. */
    private final Parser parser;

    /**
     * Initialises the Duke object by initialising all required components and loading the save file in.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser();
        try {
            storage.loadState(tasks);
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs the Duke chatbot, prompts the user for inputs to perform certain actions.
     */
    public String run() {
        return ui.welcomeMessage();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                Command c = parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e);
//            }
//        }
//        ui.sayBye();
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
