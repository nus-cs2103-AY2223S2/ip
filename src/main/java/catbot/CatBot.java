package catbot;

import catbot.commands.Command;
import catbot.parser.Parser;
import catbot.storage.Storage;
import catbot.tasklist.TaskList;
import catbot.ui.Ui;

import java.util.ArrayList;

/**
 * This is the main class
 */
public class CatBot {

    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

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
        (new CatBot("./data/tasklist.txt")).run();
    }

}