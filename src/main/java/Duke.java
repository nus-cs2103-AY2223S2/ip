import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS_SIZE = 100;
    private static ArrayList<String> tasks = new ArrayList<>(MAX_TASKS_SIZE);

    private static void printText(String text) {
        System.out.printf("     %s\n", text);
    }

    private static void printHorizontal() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printStartup() {
        String logo =
                " /\\_/\\\n" +
                "( o.o )   ~meow~\n" +
                " > ^ <";
        System.out.println(logo);
        printHorizontal();
        printText("Hello! I'm Duke");
        printText("What can I do for you?");
        printHorizontal();
    }

    private static void commandAddTask(String task) {
        printHorizontal();
        if (tasks.size() == MAX_TASKS_SIZE) {
            printText("Task list is full!");
        } else {
            tasks.add(task);
            printText(String.format("Added: %s", task));
        }
        printHorizontal();
    }

    private static void commandListTasks() {
        printHorizontal();
        for (int index = 0; index < tasks.size(); index++) {
            printText(String.format("%d. %s", index + 1, tasks.get(index)));
        }
        printHorizontal();
    }

    private static void commandExit() {
        printHorizontal();
        printText("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void main(String[] args) {
        printStartup();

        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String command = scanner.nextLine();
            switch (command) {
                case "list":
                    commandListTasks();
                    break;
                case "bye":
                    commandExit();
                    isExit = true;
                    break;
                default:
                    commandAddTask(command);
            }
        }
    }
}
