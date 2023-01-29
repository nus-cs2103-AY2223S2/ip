package catbot.ui;

import java.util.Scanner;

import org.apache.commons.text.WordUtils;

/**
 * Handles all UI features.
 */
public class Ui {
    private static final int MAX_LINE_LENGTH = 120;
    private String nextOutput;
    private final Scanner inputScanner;

    /**
     * Initialises a Ui instance.
     */
    public Ui() {
        nextOutput = "";
        inputScanner = new Scanner(System.in);
    }

    /**
     * Greets the user.
     */
    public void showWelcome() {
        String logo =
                " ____ ____ ____ ____ ____ ____\n"
                + "||C |||a |||t |||B |||o |||t ||\n"
                + "||__|||__|||__|||__|||__|||__||\n"
                + "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|\n";


        System.out.println("Hello from\n" + logo);
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Sets the next line buffer for {@code showNext}.
     * @param message is the message to show.
     */
    public void setNextOutput(String message) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int boxWidth = 0;
        for (String line: lines) {
            boxWidth = Math.max(boxWidth, Wcwidth.strWidth(line)); // strWidth for language support
        }
        boxWidth = (int) (Math.ceil(boxWidth / 10.0) * 10) + 2;
        StringBuilder output = new StringBuilder();
        output.append("╭─").append(" >^w^< ").append("─".repeat(boxWidth - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                    .append(line)
                    .append(" ".repeat(boxWidth - Wcwidth.strWidth(line) - 2))
                    .append(" │\n");
        }

        output.append("╰").append("─".repeat(boxWidth)).append("╯\n");
        nextOutput = output.toString();
    }

    /**
     * Displays errors to the user.
     * @param message is the error message.
     */
    public void displayError(String message) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int boxWidth = 0;
        for (String line: lines) {
            boxWidth = Math.max(boxWidth, Wcwidth.strWidth(line)); // strWidth for language support
        }
        boxWidth = (int) (Math.ceil(boxWidth / 10.0) * 10) + 2;
        StringBuilder output = new StringBuilder();
        output.append(ConsoleColors.RED);
        output.append("╭─").append(" >@w@< ").append("─".repeat(boxWidth - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                    .append(line)
                    .append(" ".repeat(boxWidth - Wcwidth.strWidth(line) - 2))
                    .append(" │\n");
        }
        output.append("╰").append("─".repeat(boxWidth)).append("╯\n");
        output.append(ConsoleColors.RESET);
        System.out.println(output);
    }

    /**
     * Display the next line to the user.
     * Requires {@code setNextOutput} to be called first.
     */
    public void showNext() {
        System.out.println(nextOutput);
        nextOutput = "";
    }
}
