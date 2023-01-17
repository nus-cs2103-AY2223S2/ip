import java.util.Scanner;
import java.util.ArrayList;

// Assume Duke is only a task manager.
public class Duke {
    static final String BORDER = "----------------------------------------";

    // Members
    private static ArrayList<Task> tasks = new ArrayList<>();

    // Methods
    public static void list() {
        if (tasks.size() == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        for (Task t : tasks) {
            System.out.println(t.fullMessage());
        }
        System.out.println(BORDER);
    }

    // Zero indexed
    public static void markDone(int index) {
        Task curr = tasks.get(index);
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                curr.fullMessage() + "\n" + BORDER);
    }

    // Zero indexed
    public static void markUndone(int index) {
        Task curr = tasks.get(index);
        curr.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                curr.fullMessage() + "\n" + BORDER);
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
            // More cases to be added.
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    list();
                    break;
                case "mark":
                    int index = sc.nextInt();
                    markDone(index);
                    break;
                case "unmark":
                    index = sc.nextInt();
                    markUndone(index);
                    break;
                case "todo":
                    String message = sc.nextLine().trim();
                    Task t = new ToDos(message);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n" + t.fullMessage());
                    System.out.println("Now you have " + tasks.size() + " tasks in this list\n" + BORDER);
                    break;
                case "deadline":
                    String[] messages = sc.nextLine().trim().split("/");
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
                default:
                    System.out.println("lol what\n" + BORDER);
            }
        }

    }
}
