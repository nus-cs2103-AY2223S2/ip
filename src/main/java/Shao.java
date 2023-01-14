import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Shao {
    public static void printRowLine() {
        println("________________________________________________________");
    }

    public static void println(String s) {
        System.out.println("\t" + s);
    }

    public static void printAddedTask(Task task, int tasksCnt) {
        printRowLine();
        println("Noted. I've added this task:");
        println("  " + task.toString());
        println(String.format("You have %d %s in your list currently.", tasksCnt, tasksCnt > 1 ? "tasks" : "task"));
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
            println(String.format("%d.%s", i + 1, curTask));
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

    public static String getBy(String input) {
        String[] inputArr = input.split(" ");
        int l = inputArr.length;
        for (int i = 0; i < l; i++) {
            if (i < l - 1 && inputArr[i].equals("/by"))
                return String.join(" ", Arrays.copyOfRange(inputArr, i + 1, l));
        }
        return "";
    }

    public static String[] getFromTo(String input) {
        String[] inputArr = input.split(" ");
        int l = inputArr.length;
        int fromStartIdx = -1, fromEndIdx = -1, toStartIdx = -1;
        String from = "";
        String to = "";
        for (int i = 0; i < l; i++) {
            if (i < l - 1) {
                if (inputArr[i].equals("/from")) {
                    fromStartIdx = i + 1;
                }
                if (inputArr[i].equals("/to")) {
                    fromEndIdx = i;
                    toStartIdx = i + 1;
                }
            }
        }
        if (fromStartIdx > -1 && fromEndIdx > -1) {
            from = String.join(" ", Arrays.copyOfRange(inputArr, fromStartIdx, fromEndIdx));
        }
        if (toStartIdx > -1) {
            to = String.join(" ", Arrays.copyOfRange(inputArr, toStartIdx, l));
        }
        return new String[] { from, to };
    }

    public static String trimDate(String input) {
        String[] inputArr = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : inputArr) {
            if (s.contains("/")) {
                return sb.toString().trim();
            }
            sb.append(s + " ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Task> items = new ArrayList<>();

        greetUser();

        // Prompting
        while (true) {
            String input = scan.nextLine().trim();
            String inputLower = input.toLowerCase();
            if (inputLower.isBlank())
                continue;
            switch (inputLower) {
                case "bye":
                    exitUser();
                    scan.close();
                    return;
                case "list":
                    printList(items);
                    break;
                default:
                    if (inputLower.startsWith("mark")) {
                        markItem(inputLower.split(" ")[1], items);
                    } else if (inputLower.startsWith("unmark")) {
                        unmarkItem(inputLower.split(" ")[1], items);
                    } else {
                        Task newTask = inputLower.startsWith("todo")
                                ? new Todo(input)
                                : inputLower.startsWith("deadline")
                                        ? new Deadline(trimDate(input), getBy(input))
                                        : new Event(trimDate(input), getFromTo(input));

                        items.add(newTask);
                        printAddedTask(newTask, items.size());
                    }
                    break;

            }
        }
    }
}
