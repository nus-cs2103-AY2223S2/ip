package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.io.FileStorage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.nio.file.Path;
import java.util.Scanner;

/**
 * Main class of the app.
 */
public class Duke {
    private static final Path saveFilePath = Path.of("./save-data/task-list.csv");

    private static final Ui ui = new Ui(System.out);
    private static final Parser parser = new Parser();

    private static TaskList tasks;

    /**
     * Main method for the app.
     *
     * @param args Command-line arguments.
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
        String greeting = String.format("IT IS I!\n%s\nManager of tasks! Tracker of progress!\nHeedless user, what is "
                + "it that you seek?", logo);
        ui.print(greeting);
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
