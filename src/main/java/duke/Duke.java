package duke;
import java.util.Scanner;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

import java.time.LocalDate
        ;
/** Encapsulates the Duke chat bot.
 * @author Hee Jia Yuan
 */
public class Duke {
    /** Handles storing of Tasks in the hard drive.*/
    private Storage storage;

    /** Handles the tasks within a session.  */
    private TaskList tasks;

    /** Handles interactions with the User. */
    private Ui ui;

    /**
     * Constructs a new Duke session.
     * @param filePath Directory to file storing Tasks in hard drive.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    /**
     * Runs the Duke bot.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            Parser.parse(ui, tasks, storage, userInput);
        }
    }
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}


