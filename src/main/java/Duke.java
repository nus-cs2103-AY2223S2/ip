import java.util.Scanner;

import controllers.Command;
import entities.TaskList;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import views.UI;

/**
 * Represents the Duke Chat bot.
 * Running a duke object loads data from the specified file into memory,
 * and exiting the program writes data to the hard disk.
 */
public class Duke {
    private static TaskList taskList;
    private static Scanner in;
    private static UI ui;

    /**
     * Duke Constructor for initializing the Duke Object.
     *
     * @param filename location of Storage
     */
    public Duke(String filename) {
        Storage storage = new Storage(filename);
        in = new Scanner(System.in);
        ui = new UI();
        try {
            storage.connect();
            taskList = new TaskList(storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the program. Parses the input and generates command.
     */
    public void run() {
        ui.printWelcomeMessage();
        while (in.hasNext()) {
            Command cmd = Parser.parse(in.nextLine());
            try {
                cmd.execute(() -> taskList);
                if (cmd.isTerminating()) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Main entrypoint for running the Duke chatbot.
     *
     * @param args Command Line Args
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}
