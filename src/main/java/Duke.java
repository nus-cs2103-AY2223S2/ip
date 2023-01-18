import tasks.Deadline;
import tasks.Event;
import tasks.Tasks;
import tasks.ToDo;
import java.util.Scanner;

public class Duke {

    private static final String BAR =
            "    ____________________________________________________________";

    private static final String INDENTATION = "     ";
    private static final String NEW_LINE = "\n";

    private static void greet() {
        System.out.println(BAR);
        System.out.println(INDENTATION + "Hello! I'm Duke");
        System.out.println(INDENTATION + "What can I do for you?");
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    private static void echo(String... texts) {
        System.out.println(BAR);
        for (String text : texts) {
            System.out.println(INDENTATION + text);
        }
        System.out.println(BAR);
        System.out.print(NEW_LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        Tasks tasks = new Tasks();
        while (true) {
            String input = scanner.nextLine();

            String[] inputWords = input.split(" ");

            String command = inputWords[0];

            if (command.equals("bye")) {
                echo("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if (command.equals("list")) {
                System.out.println(BAR);
                tasks.printAll();
                System.out.println(BAR);
            } else if (command.equals("mark")) {
                int taskIndex = Integer.parseInt(inputWords[1]);
                echo("Nice! I've marked this task as done:", "  " + tasks.markTask(taskIndex));
            } else if (command.equals("unmark")) {
                int taskIndex = Integer.parseInt(inputWords[1]);
                echo("OK, I've marked this task as not done yet:",
                        "  " + tasks.unmarkTask(taskIndex));
            } else if (command.equals("todo")) {
                String description = inputWords[1];
                tasks.addTask(new ToDo(description));
                echo("added: " + input);
            } else if (command.equals("deadline")) {
                String description = inputWords[1];
                String by = inputWords[2];
                tasks.addTask(new Deadline(description, by));
            } else if (command.equals("event")) {
                String description = inputWords[1];
                String from = inputWords[2];
                String to = inputWords[3];
                tasks.addTask(new Event(description, from, to));
            }
        }
    }
}
