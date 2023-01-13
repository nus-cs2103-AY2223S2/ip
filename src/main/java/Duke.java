import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

    private static final String LINE = " ".repeat(4) + "____________________________________________________________";

    private static final String INDENTATION = " ".repeat(5);

    private static final List<Task> addedTasks = new ArrayList<>();

    private static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    private static void addTask(String msg) {
        addedTasks.add(new Task(msg));
        String displayedMsg = "added: " + msg;
        echo(displayedMsg);
    }

    private static void listTasks() {
        String displayedMsg = "Here are the tasks in your list:\n" +
                IntStream.range(0, addedTasks.size()).mapToObj(
                        i -> String.format("%d.%s", i + 1, addedTasks.get(i)))
                        .collect(Collectors.joining("\n"));
        echo(displayedMsg);
    }

    // Note that, indexes start from 1
    private static void markTaskAsDone(int index) {
        Task t = addedTasks.get(index - 1);
        t.markAsDone();
        echo("Nice! I've marked this task as done:\n  " + t);
    }

    private static void markTaskAsNotDone(int index) {
        Task t = addedTasks.get(index - 1);
        t.markAsNotDone();
        echo("OK, I've marked this task as not done yet:\n  " + t);
    }

    /**
     * Extracts an index from {@code mark} or {@code unmark} command.
     * 
     * @param cmd the string represents the command
     * @return The extracted index
     */
    private static int extractIndexFromCommand(String cmd) {
        int whitespaceIndex = cmd.indexOf(" ");
        if (whitespaceIndex < 0) {
            throw new IllegalArgumentException("There is no whitespace in this command");
        }
        String intPart = cmd.substring(whitespaceIndex).strip();
        return Integer.parseInt(intPart);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            echo("Hello! I'm Duke\nWhat can I do for you?");
            do {
                String in = sc.nextLine();
                if (in.equals("bye")) {
                    echo("Bye. Hope to see you again soon!");
                    break;
                } else if (in.equals("list")) {
                    listTasks();
                } else if (in.startsWith("mark")) {
                    markTaskAsDone(extractIndexFromCommand(in));
                } else if (in.startsWith("unmark")) {
                    markTaskAsNotDone(extractIndexFromCommand(in));
                } else {
                    addTask(in);
                }
            } while (true);
        }
    }
}
