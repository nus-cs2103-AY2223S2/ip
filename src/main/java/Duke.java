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
    private static Storage storage;
    private static Scanner in;
    private static UI ui;

    /**
     * Duke Constructor for initializing the Duke Object.
     *
     * @param filename location of Storage
     */
    public Duke(String filename) {
        storage = new Storage(filename);
        in = new Scanner(System.in);
        ui = new UI();
        try {
            storage.connect();
            taskList = new TaskList((taskList) -> storage.load(taskList));
        } catch (DukeException e) {
            System.out.println("There was an error loading the data. Storage will be reset.");
            taskList = new TaskList();
        }
    }

    /**
     * Runs the program. Parses the input and generates command.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (in.hasNext() && !isExit) {
            Command cmd = Parser.parse(in.nextLine());
            try {
                cmd.execute(() -> taskList);
                if (cmd.isTerminating()) {
                    storage.write(taskList);
                    isExit = true;
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
