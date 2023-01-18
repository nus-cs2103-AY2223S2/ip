import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final List<String> TASK_LIST =  new ArrayList<>();
    private static int nTasks = 0;

    private static void greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(LOGO);
        System.out.println(greeting);
    }

    private static void exit() {
        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exitMessage);
    }

    private static void addTask(String task) {
        TASK_LIST.add(task);
        nTasks++;
        System.out.println("New task added: " + task);
    }

    private static void printTaskList() {
        for (int i = 0; i < nTasks; i++) {
            System.out.println((i + 1) + ". " + TASK_LIST.get(i));
        }
    }

    private static void processCommand(String command) {
        if (command.equals("list")) {
            printTaskList();
        } else {
            addTask(command);
        }

        System.out.print("\n");
    }

    public static void start() {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Duke.exit();
                break;
            }
            processCommand(input);
        }
    }

    public static void main(String[] args) {
        start();
    }
}
