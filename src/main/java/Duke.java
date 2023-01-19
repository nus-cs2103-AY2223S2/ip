import java.util.ArrayList;
import java.util.Scanner;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Prints a line 4 spaces away from the left edge of the screen to visually
     * separate Duke's replies from user input.
     */
    private static void printLine() {
        System.out.printf("%64s%n", "    ____________________________________________________________");
    }

    /**
     * Prints Duke's greeting message (bounded by lines above and below).
     */
    private static void greet() {
        printLine();
        System.out.printf("     %s%n", "Hello! I'm Duke");
        System.out.printf("     %s%n", "What can I do for you?");
        printLine();
    }

    /**
     *
     */
    private static void confirmAddition(Task t) {
        System.out.printf("     %s%n", "Got it. I've added this task:");
        System.out.printf("       %s%n", t.toString());
        System.out.printf("     %s%d%s%n", "Now you have ", tasks.size(), " tasks in the list.");
    }

    /**
     * Replies to user inputs according to requirements.
     * If user inputs "bye", return to exit Duke.
     * If user inputs "list", print current tasks.
     * If user inputs "mark NUMBER" or "unmark NUMBER", update the doneness of that task number.
     * If user inputs a task, add to current tasks.
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printLine();

            if (input.equals("list")) {
                System.out.printf("     %s%n", "Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("     %d.%s%n",
                            i + 1,
                            tasks.get(i).toString());
                }
            } else {
                String command = (input.split(" ")[0]).toLowerCase();
                if (command.equals("mark") || command.equals(("unmark"))) {
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(input.split(" ")[1]);
                        if (command.equals("mark")) {
                            tasks.get(taskNumber - 1).markAsDone();
                            System.out.printf("     %s%n", "Nice! I've marked this task as done:");
                            System.out.printf("       %s%n",
                                    tasks.get(taskNumber - 1).toString());
                        } else {
                            tasks.get(taskNumber - 1).markAsNotDone();
                            System.out.printf("     %s%n", "OK, I've marked this task as not done yet:");
                            System.out.printf("       %s%n",
                                    tasks.get(taskNumber - 1).toString());
                        }
                    } catch (NumberFormatException|IndexOutOfBoundsException e) {
                        System.out.printf("     %s%n", "Please input valid task number.");
                    }
                } else {
                    String description, by, from, to;
                    Task t;
                    switch (command) {
                        case "todo":
                            description = input.substring(5);
                            t = new ToDo(description);
                            tasks.add(t);
                            confirmAddition(t);
                            break;
                        case "deadline":
                            int byIndex = input.indexOf("/by");
                            description = input.substring(9, byIndex - 1);
                            by = input.substring(byIndex + 4);
                            t = new Deadline(description, by);
                            tasks.add(t);
                            confirmAddition(t);
                            break;
                        case "event":
                            int fromIndex = input.indexOf("/from");
                            int toIndex = input.indexOf("/to");
                            description = input.substring(6, fromIndex - 1);
                            from = input.substring(fromIndex + 6, toIndex - 1);
                            to = input.substring(toIndex + 4);
                            t = new Event(description, from, to);
                            tasks.add(t);
                            confirmAddition(t);
                            break;
                        default:
                            System.out.printf("     %s%n", "Please input valid task type.");
                    }
                }
            }

            printLine();
            input = sc.nextLine();
        }
        printLine();
        System.out.printf("     %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}