package catbot;

import java.util.ArrayList;

import catbot.commands.Command;
import catbot.parser.Parser;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;



/**
 * Entry point of the CatBot application.
 * Initiates all the required components and begins interaction with the user.
 */
public class CatBot {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initialises a new CatBot instance and loads from the save file.
     * @param saveFile is the path to the save file for persistent task storage.
     */
    public CatBot(String saveFile) {
        ui = new Ui();
        try {
            storage = new Storage(saveFile);
            tasks = new TaskList(storage.load());
        } catch (CatBotException e) {
            ui.displayError("Error loading from save file.");
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Interacts with the user and handles commands.
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
            } catch (CatBotException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.showNext();
            }
        }
    }


    public static void main(String[] args) {
        new CatBot("./data/tasklist.txt").run();
    }

}
