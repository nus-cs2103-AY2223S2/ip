package alfred;

import alfred.command.Command;
import alfred.exceptions.AlfredException;
import alfred.parser.Parser;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;
import javafx.application.Platform;

import java.util.concurrent.TimeUnit;

/**
 * Represents a Personal Assistant Chat-bot that helps a person to keep track of various things.
 * An <code>Alfred</code> object corresponds to the Chat-bot that will interact with the user.
 */
public class Alfred {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    private boolean isExit;

    // How to pass file path inside here? javaFx requires this constructor
    public Alfred() {
        this("data/alfred.txt");
    }

    /**
     * Constructs an Alfred object that takes in a String that represents the filepath.
     * @param filePath The filepath will be used by Alfred to store its data.
     */
    public Alfred(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (AlfredException e) {
            tasks = new TaskList();
        }
    }

    public String runIntro() {
        return ui.getOpening();
        /*
        ui.displayOpening();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AlfredException e) {
                ui.displayError(e);
            }
        }
         */
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String output = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            storage.write(tasks);
            return output;
        } catch (AlfredException e) {
            return ui.getErrorMessage(e);
        }
    }

    public boolean isExit() {
        return isExit;
    }


    public void handleExit() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage()); // how to show an error?
        } finally {
            Platform.exit();
        }
    }

    /**
     * Creates an Alfred object and run the program
     * @param args No arguments will be given into the program.
     */
    public static void main(String[] args) {
        Alfred alfred = new Alfred("data/alfred.txt");
    }
}
