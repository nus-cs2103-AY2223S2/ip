package tunabot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import tunabot.exceptions.InputException;
import tunabot.gui.Gui;


/**
 * Main class for TunaBot
 */
public class TunaBot {
    private static final Scanner s = new Scanner(System.in);
    private static TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a TunaBot with the given save path.
     */
    public TunaBot() {

        Path savePath = Paths.get("data", "save.txt");
        ui = new Ui();
        Storage storage = new Storage(savePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.saveFileProblem();
        }
        assert tasks != null : "Storage should have loaded either an existing "
            + "task list from save file or initialised a new task list";
    }

    /**
     * Main class to initialize and run TunaBot
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
    public String getResponse(String input) {
        try {
            String output = Parser.parse(input, tasks);
            if (input.equals("bye")) {
                Storage.save(tasks);
                Platform.exit();
            }
            return output;
        } catch (InputException e) {
            return e.getMessage();
        }
    }
}
