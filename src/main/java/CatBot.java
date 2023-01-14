import java.util.Objects;
import java.util.Scanner;
import org.apache.commons.text.WordUtils;

public class CatBot {
    private static final int MAX_LINE_LENGTH = 80;

    public static void main(String[] args) {
        String logo =
                " ____ ____ ____ ____ ____ ____ \n" +
                "||C |||a |||t |||B |||o |||t ||\n" +
                "||__|||__|||__|||__|||__|||__||\n" +
                "|/__\\|/__\\|/__\\|/__\\|/__\\|/__\\|\n";


        System.out.println("Hello from\n" + logo);

        Scanner inputScanner = new Scanner(System.in);
        String input;

        // Main loop
        do {
            input = inputScanner.nextLine();
            System.out.println(prettyOutput(get_output(input)));
        } while (!Objects.equals(input, "bye"));
    }


    /**
     * The brains of the chatbot
     * Processes the input and returns corresponding output
     * @param command is the input given by the user
     * @return what CatBot should say
     */
    private static String get_output (String command) {
        if (Objects.equals(command, "bye")) {
            return "Nice to see mew!";
        } else {
            return command;
        }
    }

    /**
     * Gives Duke's output in a pretty format
     * @param message is the message to pretty print
     * @return a string containing the message pretty printed
     */
    private static String prettyOutput(String message) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int box_width = 0;
        for (String line: lines) {
            box_width = Math.max(box_width, Wcwidth.strWidth(line)); // strWidth for language support
        }
        box_width = (int) (Math.ceil(box_width / 10.0) * 10);
        StringBuilder output = new StringBuilder();
        output.append("╭─").append(" >^w^< ").append("─".repeat(box_width - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                  .append(line)
                  .append(" ".repeat(box_width - Wcwidth.strWidth(line) - 2))
                  .append(" │\n");
        }

        output.append("╰").append("─".repeat(box_width)).append("╯\n");
        return output.toString();
    }

}