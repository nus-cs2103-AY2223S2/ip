package duke.ui.cli;

import duke.ui.Ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Handles displaying of messages and user interactions via the command-line interface.
 */
public class Cli implements Ui {
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
     * @param exitConditionChecker Given the user's input, checks if the app should exit.
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
        printStream.println("    ______________________________________________________________________");
        printStream.printf("     %s\n", message.replace("\n", "\n     "));
        printStream.println("    ______________________________________________________________________\n");
    }
}
