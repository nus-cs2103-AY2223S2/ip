import java.text.ParseException;
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
        println(task.toString());
        printRowLine();
    }

    public static void printUnmarkedTask(Task task) {
        printRowLine();
        println("OK, I've marked this task as not done yet:");
        println(task.toString());
        printRowLine();
    }

    public static void printItemDeleted(Task task, int tasksCnt) {
        printRowLine();
        println("Sure, I've removed this task:");
        println("  " + task.toString());
        println(String.format("You have %d %s in your list currently.", tasksCnt, tasksCnt > 1 ? "tasks" : "task"));
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

    public static void printError(String errorMessage) {
        printRowLine();
        println(errorMessage);
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

    public static void markItem(String itemNum, List<Task> items, boolean isMark) throws ParseException {
        int idx = Integer.parseInt(itemNum) - 1;
        Task task = items.get(idx);
        if (isMark) {
            task.markAsDone();
            printMarkedTask(task);
            return;
        }
        task.markAsUndone();
        printUnmarkedTask(task);
    }

    public static void deleteItem(String itemNum, List<Task> items) throws ParseException {
        int idx = Integer.parseInt(itemNum) - 1;
        printItemDeleted(items.remove(idx), items.size());
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
        int fromStartIdx = -1, fromEndIdx = l, toStartIdx = -1;
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
        if (fromStartIdx > -1) {
            from = sliceArrAndConcate(inputArr, fromStartIdx, fromEndIdx);
        }
        if (toStartIdx > -1) {
            to = sliceArrAndConcate(inputArr, toStartIdx, l);
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

    public static String sliceArrAndConcate(String[] arr, int startIdx, int endIdx) {
        return String.join(" ", Arrays.copyOfRange(arr, startIdx, endIdx));
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
                    boolean isDeleteOperation = inputLower.startsWith("delete");
                    boolean isMarkOperation = inputLower.startsWith("mark")
                            || inputLower.startsWith("unmark");
                    String[] inputArr = input.split(" ");
                    if (isDeleteOperation || isMarkOperation) {
                        if (inputArr.length < 2) {
                            printError("Oops! The item number cannot be empty.");
                        } else {
                            try {
                                if (isDeleteOperation) {
                                    deleteItem(inputArr[1], items);
                                } else {
                                    markItem(inputArr[1], items, inputLower.startsWith("mark"));
                                }
                            } catch (ParseException ex) {
                                printError("Oops! An item number must be provided.");
                            }
                        }
                    } else if (inputLower.startsWith("todo") || inputLower.startsWith("deadline")
                            || inputLower.startsWith("event")) {

                        // Todo - 0, Deadline - 1, Event - 2
                        int operationType = inputLower.startsWith("todo")
                                ? 0
                                : inputLower.startsWith("deadline")
                                        ? 1
                                        : 2;

                        if (inputArr.length < 2) {
                            printError(String.format("Oops! The description of a %s cannot be empty.",
                                    operationType == 0 ? "todo" : operationType == 1 ? "deadline" : "event"));
                        } else {
                            Task newTask = null;
                            String description = sliceArrAndConcate(inputArr, 1, inputArr.length);
                            switch (operationType) {
                                case 0:
                                    newTask = new Todo(description);
                                    break;

                                case 1:
                                    String by = getBy(input);
                                    if (by.isEmpty()) {
                                        printError(
                                                "Oops! The description of deadline must include a completion date/time.");
                                        continue;
                                    }
                                    newTask = new Deadline(trimDate(description), by);
                                    break;

                                case 2:
                                    String[] fromTo = getFromTo(input);
                                    if (fromTo[0].isEmpty()) {
                                        printError("Oops! The description of event must include a from date/time.");
                                        continue;
                                    }
                                    if (fromTo[1].isEmpty()) {
                                        printError("Oops! The description of event must include a to date/time.");
                                        continue;
                                    }
                                    newTask = new Event(trimDate(description), fromTo);
                                    break;

                                default:
                                    break;
                            }
                            items.add(newTask);
                            printAddedTask(newTask, items.size());
                        }
                    } else {
                        printError("Oops! I'm sorry but I don't know what that means.");
                    }
                    break;

            }
        }
    }
}
