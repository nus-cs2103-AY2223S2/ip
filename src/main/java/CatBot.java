import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import org.apache.commons.text.WordUtils;

public class CatBot {
    private static final int MAX_LINE_LENGTH = 80;
    static ArrayList<Task> tasks = new ArrayList<>();

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
            try {
                System.out.println(prettyOutput(get_output(input), false));
            } catch (CatBotException e) {
                System.out.println(prettyOutput(e.getMessage(), true));
            }
        } while (!Objects.equals(input, "bye"));
    }


    /**
     * The brains of the chatbot
     * Processes the input and returns corresponding output
     * @param command is the input given by the user
     * @return what CatBot should say
     */
    private static String get_output (String command) throws CatBotException {
        String[] cmd = command.split(" ", 2);
        int index;
        switch (cmd[0].strip().toLowerCase(Locale.ROOT)) {
            case "todo":
            case "deadline":
            case "event":
                Task newTask = Task.fromCommand(command);
                tasks.add(newTask);
                return "Added new task!\n    " +
                       newTask +
                       "\nYou have " + tasks.size() + (tasks.size() > 1 ? " tasks now." : " task now.");

            case "list":
                StringBuilder taskList = new StringBuilder("List of tasks: \n");
                index = 1;
                for (Task task: tasks) {
                    taskList.append("  ").append(index++).append(".").append(task).append("\n");
                }
                return taskList.toString();

            case "mark":
                try {
                    index = Integer.parseInt(cmd[1].strip());
                } catch (NumberFormatException e) {
                    throw new CatBotException(e + " isn't a number! >@w@<");
                }

                tasks.get(index - 1).setDone(true);
                return "Marked the task as done!";

            case "unmark":
                try {
                    index = Integer.parseInt(cmd[1].strip());
                } catch (NumberFormatException e) {
                    throw new CatBotException(e + " isn't a number! >@w@<");
                }

                tasks.get(index - 1).setDone(false);
                return "Unmarked the task!";

            case "delete":
                try {
                    index = Integer.parseInt(cmd[1].strip());
                } catch (NumberFormatException e) {
                    throw new CatBotException(e + " isn't a number! >@w@<");
                }

                tasks.remove(index - 1);
                return "Deleted the task!";

            case "echo":
                return cmd[1];

            case "bye":
                return "Nice to meet mew!";

            default:
                throw new CatBotException("I don't know what you mean >@w@<");
        }
    }

    /**
     * Gives Duke's output in a pretty format
     * @param message is the message to pretty print
     * @return a string containing the message pretty printed
     */
    private static String prettyOutput(String message, boolean error) {
        message = message.strip();
        String[] lines = WordUtils.wrap(message, MAX_LINE_LENGTH).split("\n");
        int box_width = 0;
        for (String line: lines) {
            box_width = Math.max(box_width, Wcwidth.strWidth(line)); // strWidth for language support
        }
        box_width = (int) (Math.ceil(box_width / 10.0) * 10) + 5;
        StringBuilder output = new StringBuilder();
        if (error) {
            output.append(ConsoleColors.RED);
        }
        output.append("╭─").append(" >^w^< ").append("─".repeat(box_width - 8)).append("╮\n");
        for (String line: lines) {
            output.append("│ ")
                  .append(line)
                  .append(" ".repeat(box_width - Wcwidth.strWidth(line) - 2))
                  .append(" │\n");
        }

        output.append("╰").append("─".repeat(box_width)).append("╯\n");
        if (error) {
            output.append(ConsoleColors.RESET);
        }
        return output.toString();
    }
}