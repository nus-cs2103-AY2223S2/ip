package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

/**
 * A chatbot that receives user's input on various predetermined
 * command types and performs relevant functions.
 */
public class Duke {
    private static TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;

    /**
     * Initializes a bot with provided path to the storage space.
     *
     * @param filePath Path to the storage file.
     * @param directoryPath Path to the storage directory.
     */
    public Duke(String filePath, String directoryPath) {
        ui = new Ui();
        storage = new Storage(filePath, directoryPath);
        tasks = new TaskList();
        parser = new Parser(tasks, storage);
        try {
            tasks = storage.loadTasks();
        } catch (DukeException | IOException e) {
            Ui.print(String.valueOf(e));
        }
    }

    /**
     * Runs the bot and handles users' inputs
     */
    public void run() {
        Ui.greet();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            parser.parse(command);
            command = sc.nextLine();
        }
        Ui.goodbye();
    }

    /**
     * Initiates the bot.
     *
     * @param args Supplied command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt", "/data").run();
    }

    public String getResponse(String input) {
        String res = parser.parse(input);
        return res;
    }
}
