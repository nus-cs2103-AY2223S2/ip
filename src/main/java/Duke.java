import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Main application class.
 */
//Follow the given coding standard.
public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates <case>Duke</case> object.
     *
     * @param filePath To specific a file path to save the previous records.
     */
    public Duke(String filePath) {
        ui = new Ui();

        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.loadRecord());
            tasks.printList();
            ui.printDashes();
        } catch (IOException e) {
            System.out.println("Error occurs when try to load.");
            tasks = new TaskList();
        }
    }

    /**
     * Main application loop.
     * Gets the user input to trigger the run.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            if (input.equals("bye")) {
                break;
            }
            try {
                if (Parser.parseInput(tasks, input)) {
                    storage.writeToFile(tasks);
                }
            } catch (DukeException e) {
                ui.println(e.getMessage());
            } catch (NumberFormatException e) {
                ui.println("The operation must follow by a integer");
            } catch (IOException e) {
                ui.println("Error occurs when try to access your file");
            } catch (DateTimeException e) {
                ui.println("Invalid date time format, please try again!");
            }
            ui.printDashes();
        }
    }

    /**
     * Entry point to the application.
     *
     * @param args Optional arguments.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./userRecords/records.txt");
        duke.run();
    }
}
