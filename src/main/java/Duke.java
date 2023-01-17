import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    protected static String indent = "     ";
    protected static String divider = indent + "____________________________________________________________";
    protected static ArrayList<Task> tasks =  new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String[] command = sc.nextLine().split(" ", 2);
            if (command[0].equals("bye")) {
                System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                break;
            } else if (command[0].equals("list")) {
                System.out.println(formatMessage(listTasks()));
            } else if (command[0].equals("mark")) {
                int taskNum = Integer.parseInt(command[1]) - 1;
                tasks.get(taskNum).mark();
                System.out.println(formatMessage("Nice! I've marked this task as done:\n" +
                        indent + tasks.get(taskNum).toString()));
            } else if (command[0].equals("unmark")) {
                int taskNum = Integer.parseInt(command[1]) - 1;
                tasks.get(taskNum).unmark();
                System.out.println(formatMessage("OK, I've marked this task as not done yet:\n" +
                        indent + tasks.get(taskNum).toString()));
            } else {
                switch (command[0]) {
                    case "todo":
                        tasks.add(new Todo(command[1]));
                        break;
                    case "deadline": {
                        String[] arguments = command[1].split(" /by ");
                        tasks.add(new Deadline(arguments[0], arguments[1]));
                        break;
                    }
                    case "event": {
                        String[] arguments = command[1].split(" /from ");
                        String[] timings = arguments[1].split(" /to ");
                        tasks.add(new Event(arguments[0], timings[0], timings[1]));
                        break;
                    }
                    default:
                        System.out.println(formatMessage("I do not understand"));
                }

                System.out.println(formatMessage("Got it. I've added this task:\n" +
                        indent + indent + tasks.get(tasks.size() - 1).toString() + "\n" +
                        indent + "Now you have " + tasks.size() + " task(s) in the list."));
            }
        }
    }

    public static void printWelcomeMessage() {
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println(divider);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(divider);
    }

    public static String formatMessage(String message) {
        return divider + "\n" + indent + message + "\n" + divider;
    }

    public static String listTasks() {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += indent + (i + 1) + ". " + tasks.get(i).toString();
            if (i < tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }
}
