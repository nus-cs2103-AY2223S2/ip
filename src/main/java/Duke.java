import java.util.Scanner;

// Assume Duke is only a task manager.
public class Duke {
    // Members
    private static Task[] tasks = new Task[101];
    private static int size = 0;

    // Methods
    public static void list() {
        if (size == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        for (int i = 1; i <= size; i++) {
            Task curr = tasks[i];
            System.out.println(i + ". " + curr.fullMessage());
        }
    }

    public static void markDone(int index) {
        Task curr = tasks[index];
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                curr.fullMessage());
    }

    public static void markUndone(int index) {
        Task curr = tasks[index];
        curr.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                curr.fullMessage());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?");
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
                    size++;
                    tasks[size] = new ToDos(message);
                    System.out.println("Got it. I've added this task:\n" + tasks[size].fullMessage());
                    System.out.println("Now you have " + size + " tasks in this list");
                    break;
                case "deadline":
                    String[] messages = sc.nextLine().trim().split("/");
                    size++;
                    tasks[size] = new Deadlines(messages);
                    System.out.println("Got it. I've added this task:\n" + tasks[size].fullMessage());
                    System.out.println("Now you have " + size + " tasks in this list");
                    break;
                case "event":
                    messages = sc.nextLine().trim().split("/");
                    size++;
                    tasks[size] = new Events(messages);
                    System.out.println("Got it. I've added this task:\n" + tasks[size].fullMessage());
                    System.out.println("Now you have " + size + " tasks in this list");
                    break;
                default:
                    System.out.println("lol what");
            }
        }

    }
}
