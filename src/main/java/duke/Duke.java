/**
 * Project Name: Duke
 * Duke is a Chatbot that helps to keep track of stuff to do.
 *
 * @author Darius Ng Teng Wee
 */
package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author dfordarius
 */

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * A constructor for Duke.
     *
     * @param filepath the filepath where data is saved.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.loadTaskListFromFile());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * A method to start running Duke.
     */
    public void start() {
        ui.welcomeMessage();
        ui.showListFromStorage(storage);
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method of Duke.
     *
     * @param args The command line arguments.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) {
        String filepath = System.getProperty("user.home") + "/data/data.txt";
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Duke(filepath).start();
    }
}
