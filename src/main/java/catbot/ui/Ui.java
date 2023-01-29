package catbot.ui;

import org.apache.commons.text.WordUtils;

import java.util.Scanner;

public class Ui {
    private static final int MAX_LINE_LENGTH = 120;
    private String nextOutput;
    private final Scanner inputScanner;

    public Ui() {
        nextOutput = "";
        inputScanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo =
                " ____ ____ ____ ____ ____ ____\n" +
                        "||C |||a |||t |||B |||o |||t ||\n" +
                        "||__|||__|||__|||__|||__|||__||\n" +
                        "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|\n";


        System.out.println("Hello from\n" + logo);
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    /**
     * Gives CatBot's output in a pretty format
     * @param message is the message to pretty print
     */
    public void setNextOutput(String message) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int box_width = 0;
        for (String line: lines) {
            box_width = Math.max(box_width, Wcwidth.strWidth(line)); // strWidth for language support
        }
        box_width = (int) (Math.ceil(box_width / 10.0) * 10) + 2;
        StringBuilder output = new StringBuilder();
        output.append("╭─").append(" >^w^< ").append("─".repeat(box_width - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                    .append(line)
                    .append(" ".repeat(box_width - Wcwidth.strWidth(line) - 2))
                    .append(" │\n");
        }

        output.append("╰").append("─".repeat(box_width)).append("╯\n");
        nextOutput = output.toString();
    }

    /**
     * Gives CatBot's output in a pretty format
     * @param message is the message to pretty print
     */
    public void displayError(String message) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int box_width = 0;
        for (String line: lines) {
            box_width = Math.max(box_width, Wcwidth.strWidth(line)); // strWidth for language support
        }
        box_width = (int) (Math.ceil(box_width / 10.0) * 10) + 2;
        StringBuilder output = new StringBuilder();
        output.append(ConsoleColors.RED);
        output.append("╭─").append(" >@w@< ").append("─".repeat(box_width - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                    .append(line)
                    .append(" ".repeat(box_width - Wcwidth.strWidth(line) - 2))
                    .append(" │\n");
        }
        output.append("╰").append("─".repeat(box_width)).append("╯\n");
        output.append(ConsoleColors.RESET);
        System.out.println(output);
    }

    public void showNext() {
        System.out.println(nextOutput);
        nextOutput = "";
    }
}
