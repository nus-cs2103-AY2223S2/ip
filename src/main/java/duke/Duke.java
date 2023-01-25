package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    static protected final String DATA_DIR = "data/";
    static protected final String DATA_FILENAME = "duke.txt";
    static protected final String WELCOME_MESSAGE = "Hello! I'm Duke\n\tWhat can I do for you?";

    /**
     * Returns a Duke object.
     * @param filePath The file path where the data file is located.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the task bot.
     */
    public void run() {
        ui.showNormalMessage(WELCOME_MESSAGE);
        Scanner inputScanner = new Scanner(System.in);
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            try {
                parser.parseString(inputStr, tasks, ui);
                storage.save(tasks);
            } catch (BadCommandException | DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
            inputStr = inputScanner.nextLine().trim();
        }
        inputScanner.close();
        ui.showNormalMessage("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        new Duke(DATA_DIR + DATA_FILENAME).run();
    }
}
