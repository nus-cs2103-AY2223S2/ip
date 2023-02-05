package duke.ui.cli;

import duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Handles user interactions and displaying of messages via a CLI.
 */
public class Cli implements Ui {
    private static final String MESSAGE_BORDER =
            "    ______________________________________________________________________";

    private final PrintStream printStream;
    private final Scanner scanner;

    private final BiConsumer<String, Consumer<String>> inputHandler;
    private final Function<String, Boolean> exitConditionChecker;

    /**
     * Creates a Cli object.
     *
     * @param printStream The stream to print messages to.
     * @param inputStream The stream to read the user's input from.
     * @param inputHandler Handles the user's inputs.
     * @param exitConditionChecker Checks if the app should exit base on the given user input.
     */
    public Cli(PrintStream printStream, InputStream inputStream, BiConsumer<String, Consumer<String>> inputHandler,
               Function<String, Boolean> exitConditionChecker) {
        assert printStream != null;
        assert inputStream != null;
        assert inputHandler != null;
        assert exitConditionChecker != null;

        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
        this.inputHandler = inputHandler;
        this.exitConditionChecker = exitConditionChecker;
    }

    @Override
    public void start() {
        while (true) {
            String input = scanner.nextLine();

            inputHandler.accept(input, this::print);

            if (exitConditionChecker.apply(input)) {
                break;
            }
        }

        scanner.close();
    }

    @Override
    public void print(String message) {
        printStream.printf("%s\n     %s\n%s\n\n", MESSAGE_BORDER, message.replace("\n", "\n     "), MESSAGE_BORDER);
    }
}
