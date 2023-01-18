import java.util.Scanner;
import java.util.ArrayList;

// Assume Duke is only a task manager.
public class Duke {
    // Attribute
    static final String BORDER = "----------------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<>();

    // Methods
    // List out all tasks and their rank.
    public static void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        int rank = 1;
        for (Task t : tasks) {
            System.out.println(rank + "." + t.fullMessage());
            rank++;
        }
        System.out.println(BORDER);
    }

    // Mark task at index to be done
    public static void markDone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index");
        }

        Task curr = tasks.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                curr.fullMessage() + "\n" + BORDER);
    }

    // Mark task at index to be undone
    public static void markUndone(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }

        Task curr = tasks.get(index);
        curr.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                curr.fullMessage() + "\n" + BORDER);
    }

    // Returns the string representation of the task's full message
    public static String delete(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("OOPS!!! Invalid index.");
        }
        return tasks.remove(index).fullMessage();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n" + BORDER);
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.next();
            // Useful variables
            int rank;
            String[] messages;
            String message;

            try {
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    case "list":
                        list();
                        break;
                    case "mark":
                        try {
                            rank = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            throw new DukeException("OOPS! mark must have an integer rank");
                        }
                        markDone(rank - 1);
                        break;
                    case "unmark":
                        try {
                            rank = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            throw new DukeException("OOPS! unmark must have an integer rank");
                        }
                        markUndone(rank - 1);
                        break;
                    case "todo":
                        message = sc.nextLine().trim();
                        Task t = new ToDos(message);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
                        System.out.println("Now you have " + tasks.size() + " tasks in this list\n" + BORDER);
                        break;
                    case "deadline":
                        messages = sc.nextLine().trim().split("/");
                        t = new Deadlines(messages);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
                        System.out.println("Now you have " + tasks.size() + " tasks in this list\n" + BORDER);
                        break;
                    case "event":
                        messages = sc.nextLine().trim().split("/");
                        t = new Events(messages);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:\n" + t.fullMessage());
                        System.out.println("Now you have " + tasks.size() + " tasks in this list\n" + BORDER);
                        break;
                    case "delete":
                        try {
                            rank = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            throw new DukeException("OOPS! delete must have an integer rank");
                        }
                        message = delete(rank - 1);
                        System.out.println("Noted. I've removed this task:\n" + message + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.\n" + BORDER);
                        break;
                    default:
                        System.out.println("lol what\n" + BORDER);
                        sc.nextLine();
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage() + "\n" + BORDER);
            }

        }

    }
}
