import java.util.Scanner;

// Assume Duke is only a task manager.
public class Duke {
    private static Task[] tasks = new Task[101];
    private static int size = 0;

    public static void addTask(String s) {
        size++;
        tasks[size] = new Task(s);
        System.out.println("added: " + s);
    }

    public static void list() {
        if (size == 0) {
            System.out.println("No tasks left :)");
            return;
        }
        for (int i = 1; i <= size; i++) {
            Task curr = tasks[i];
            System.out.println(i + ". [" + curr.getStatusIcon() + "] " + curr);
        }
    }

    public static void markDone(int index) {
        Task curr = tasks[index];
        curr.markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" +
                "[" + curr.getStatusIcon() + "] " + curr);
    }

    public static void markUndone(int index) {
        Task curr = tasks[index];
        curr.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "[" + curr.getStatusIcon() + "] " + curr);
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
                default:
                    command += sc.nextLine();
                    addTask(command);
                    break;
            }
        }

    }
}
