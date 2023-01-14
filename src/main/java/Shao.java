import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shao {
    public static void printRowLine() {
        println("_________________________________________");
    }

    public static void println(String s) {
        System.out.println("\t" + s);
    }

    public static void printAddedInput(String s) {
        printRowLine();
        println(String.format("added: %s", s));
        printRowLine();
    }

    public static void printMarkedTask(Task task) {
        printRowLine();
        println("Nice! I've marked this task as done:");
        println(String.format("[%s] %s", task.getStatusIcon(), task.description));
        printRowLine();
    }

    public static void printUnmarkedTask(Task task) {
        printRowLine();
        println("OK, I've marked this task as not done yet:");
        println(String.format("[%s] %s", task.getStatusIcon(), task.description));
        printRowLine();
    }

    public static void printList(List<Task> items) {
        printRowLine();
        println("Here are the tasks in your list: ");
        for (int i = 0; i < items.size(); i++) {
            Task curTask = items.get(i);
            println(String.format("%d.[%s] %s", i + 1, curTask.getStatusIcon(), curTask.description));
        }
        printRowLine();
    }

    public static void greetUser() {
        printRowLine();
        println("\tHi There! I'm Shao");
        println("\tWhat can I do for you?");
        printRowLine();
    }

    public static void exitUser() {
        printRowLine();
        println("Bye! Have a nice day!");
        printRowLine();
    }

    public static void markItem(String itemNum, List<Task> items) {
        int idx = Integer.parseInt(itemNum) - 1;
        Task task = items.get(idx);
        task.markAsDone();
        printMarkedTask(task);
    }

    public static void unmarkItem(String itemNum, List<Task> items) {
        int idx = Integer.parseInt(itemNum) - 1;
        Task task = items.get(idx);
        task.markAsUndone();
        printUnmarkedTask(task);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Task> items = new ArrayList<>();

        greetUser();

        // Prompting
        while (true) {
            String input = scan.nextLine().trim().toLowerCase();
            if (input.isBlank())
                continue;
            switch (input) {
                case "bye":
                    exitUser();
                    scan.close();
                    return;
                case "list":
                    printList(items);
                    break;
                default:
                    if (input.startsWith("mark")) {
                        markItem(input.split(" ")[1], items);
                    } else if (input.startsWith("unmark")) {
                        unmarkItem(input.split(" ")[1], items);
                    } else {
                        items.add(new Task(input));
                        printAddedInput(input);
                    }
                    break;

            }
        }
    }
}
