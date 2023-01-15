import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int MAX_TASKS_SIZE = 100;
    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS_SIZE);

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

    private static void commandAddTask(String description) {
        printHorizontal();
        if (tasks.size() == MAX_TASKS_SIZE) {
            printText("Task list is full!");
        } else {
            tasks.add(new Task(description));
            printText(String.format("Added: %s", description));
        }
        printHorizontal();
    }

    private static void commandListTasks() {
        printHorizontal();
        for (int index = 0; index < tasks.size(); index++) {
            printText(String.format("%d. %s", index + 1, tasks.get(index).toString()));
        }
        printHorizontal();
    }

    private static void commandMark(int taskNo) {
        if (taskNo > 0 && taskNo <= tasks.size()) {
            Task task = tasks.get(taskNo - 1);
            task.setIsDone(true);

            printHorizontal();
            printText("Nice! I've marked this task as done:");
            printText(task.toString());
            printHorizontal();
        } else {
            printHorizontal();
            printText("Invalid task number!");
            printHorizontal();
        }
    }

    private static void commandUnmark(int taskNo) {
        if (taskNo > 0 && taskNo <= tasks.size()) {
            Task task = tasks.get(taskNo - 1);
            task.setIsDone(false);

            printHorizontal();
            printText("OK, I've marked this task as not done yet:");
            printText(task.toString());
            printHorizontal();
        } else {
            printHorizontal();
            printText("Invalid task number!");
            printHorizontal();
        }
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
            String input = scanner.nextLine();
            String[] inputTokens = input.split(" ");

            switch (inputTokens[0]) {
                case "list":
                    commandListTasks();
                    break;
                case "mark":
                    commandMark(Integer.parseInt(inputTokens[1]));
                    break;
                case "unmark":
                    commandUnmark(Integer.parseInt(inputTokens[1]));
                    break;
                case "bye":
                    commandExit();
                    isExit = true;
                    break;
                default:
                    commandAddTask(input);
            }
        }
    }
}
