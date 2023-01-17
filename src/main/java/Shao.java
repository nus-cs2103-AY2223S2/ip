import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Shao {
    public static String sep = File.separator;
    public static String dataFilePath = "data" + sep + "shao.txt";

    enum TaskType {
        TODO, DEADLINE, EVENT
    }

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
                return sliceArrAndConcate(inputArr, i + 1, l);
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

    public static void fetchData(String input, List<Task> items) {
        String inputLower = input.toLowerCase();
        if (inputLower.isBlank())
            return;
        String[] inputArr = inputLower.split("\\|");
        TaskType operationType = inputLower.startsWith("t")
                ? TaskType.TODO
                : inputLower.startsWith("d")
                        ? TaskType.DEADLINE
                        : TaskType.EVENT;

        Task newTask = null;

        switch (operationType) {
            case TODO:
                newTask = new Todo(inputArr[2]);
                break;

            case DEADLINE:
                newTask = new Deadline(inputArr[2], inputArr[3]);
                break;

            case EVENT:
                newTask = new Event(inputArr[2], new String[] { inputArr[3], inputArr[4] });
                break;

            default:
                break;
        }
        if (inputArr[1].equals("1"))
            newTask.markAsDone();
        items.add(newTask);
    }

    public static void readInput(List<Task> items) {
        Scanner scan = new Scanner(System.in);

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

                        TaskType operationType = inputLower.startsWith("todo")
                                ? TaskType.TODO
                                : inputLower.startsWith("deadline")
                                        ? TaskType.DEADLINE
                                        : TaskType.EVENT;

                        if (inputArr.length < 2) {
                            printError(String.format("Oops! The description of a %s cannot be empty.",
                                    operationType == TaskType.TODO ? "todo"
                                            : operationType == TaskType.DEADLINE ? "deadline" : "event"));
                        } else {
                            Task newTask = null;
                            String description = sliceArrAndConcate(inputArr, 1, inputArr.length);
                            switch (operationType) {
                                case TODO:
                                    newTask = new Todo(description);
                                    break;

                                case DEADLINE:
                                    String by = getBy(input);
                                    if (by.isEmpty()) {
                                        printError(
                                                "Oops! The description of deadline must include a completion date/time.");
                                        continue;
                                    }
                                    newTask = new Deadline(trimDate(description), by);
                                    break;

                                case EVENT:
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
                            saveNewData(newTask);
                            printAddedTask(newTask, items.size());
                        }
                    } else {
                        printError("Oops! I'm sorry but I don't know what that means.");
                    }
                    break;

            }
        }
    }

    public static <T extends Task> void saveNewData(T task) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFilePath, true))) {
            bw.write(task.getSavedFormat());
            bw.newLine();
        } catch (IOException e) {
            printError("Something went wrong while saving the new task.");
        }
    }

    public static void main(String[] args) {
        List<Task> items = new ArrayList<>();
        greetUser();

        try {
            File myObj = new File(dataFilePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                fetchData(myReader.nextLine().trim(), items);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
        } finally {
            readInput(items);
        }

    }

}
