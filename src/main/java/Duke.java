import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> listOfTasks = new ArrayList<>();

    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm C-3PO, Human Cyborg Relations.\n\tWhat can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void saveTask(Task task) {
        listOfTasks.add(task);
        printLine();
        System.out.println("\tadded: " + task.description);
        printLine();
    }

    public static void listTasks() {
        Task task;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            System.out.println("\t" + i + ".[" + task.getStatusIcon() + "] " + task.description);
        }
        printLine();
    }

    public static void markTask(Task task) {
        task.markAsDone();
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  [" + task.getStatusIcon() + "] " + task.description);
        printLine();
    }

    public static void unmarkTask(Task task) {
        task.markAsUndone();
        printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  [" + task.getStatusIcon() + "] " + task.description);
        printLine();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int index;
        greet();
        while (true) {
            String command = input.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (command.split(" ")[0].equalsIgnoreCase("mark")) {
                index = Integer.parseInt(command.split(" ")[1]);
                markTask(listOfTasks.get(index - 1));
            } else if (command.split(" ")[0].equalsIgnoreCase("unmark")) {
                index = Integer.parseInt(command.split(" ")[1]);
                unmarkTask(listOfTasks.get(index - 1));
            } else {
                saveTask(new Task(command));
            }
        }
        input.close();
    }
}
