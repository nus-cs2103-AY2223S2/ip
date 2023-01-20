package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.io.FileStorage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Main class for the chatbot
 */
public class Duke {
    private final static Path saveFilePath = Path.of("./save-data/task-list.csv");

    private final static Ui ui = new Ui();
    private final static Parser parser = new Parser();

    private static TaskList tasks;

    /**
     * Main method for the chatbot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        if (!setup()) {
            return;
        }

        printGreeting();
        runInputLoop();
    }

    private static boolean setup() {
        try {
            tasks = new TaskList(new FileStorage(saveFilePath));
            return true;
        } catch (DukeException e) {
            ui.print(e.getMessage());
            return false;
        }
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        ui.print(String.format("Hello from\n%s\nWhat can I do for you?", logo));
    }

    private static void runInputLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            String message;
            try {
                message = parser.getCommand(input).run(input, tasks);
            } catch (DukeException e) {
                ui.print(e.getMessage());
                continue;
            }

            ui.print(message);

            if (parser.isByeCommand(input)) {
                break;
            }
        }

        scanner.close();
    }
}
