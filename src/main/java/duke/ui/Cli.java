package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Handles displaying of messages to the user via a command-line interface.
 */
public class Cli implements Ui {
    private final PrintStream printStream;
    private final Scanner scanner;

    /**
     * Creates a Cli object.
     *
     * @param printStream The stream to print messages to.
     * @param inputStream The stream to read the user's input from.
     */
    public Cli(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public void print(String message) {
        printStream.println("    ______________________________________________________________________");
        printStream.printf("     %s\n", message.replace("\n", "\n     "));
        printStream.println("    ______________________________________________________________________\n");
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    @Override
    public void close() {
        scanner.close();
    }
}
