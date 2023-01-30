package duke.ui.cli;

import duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Handles displaying of messages to the user via a command-line interface.
 */
public class Cli implements Ui {
    private final PrintStream printStream;
    private final Scanner scanner;

    private final Function<String, String> inputHandler;
    private final Function<String, Boolean> exitConditionChecker;

    /**
     * Creates a Cli object.
     *
     * @param printStream The stream to print messages to.
     * @param inputStream The stream to read the user's input from.
     */
    public Cli(PrintStream printStream, InputStream inputStream, Function<String, String> inputHandler,
               Function<String, Boolean> exitConditionChecker) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
        this.inputHandler = inputHandler;
        this.exitConditionChecker = exitConditionChecker;
    }

    @Override
    public void start() {
        while (true) {
            String input = scanner.nextLine();

            print(inputHandler.apply(input));

            if (exitConditionChecker.apply(input)) {
                break;
            }
        }

        scanner.close();
    }

    @Override
    public void print(String message) {
        printStream.println("    ______________________________________________________________________");
        printStream.printf("     %s\n", message.replace("\n", "\n     "));
        printStream.println("    ______________________________________________________________________\n");
    }
}
