import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum TypeOfTask {
        TODO, DEADLINE, EVENT
    }
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

    public static void saveTask(String command) throws DukeException {
        int startIdx, endIdx;
        Task task;
        String description, by, from, to;
        String[] input = command.split(" ");
        TypeOfTask taskType = null;

        if (input[0].equalsIgnoreCase("todo")) {
            taskType = TypeOfTask.TODO;
        } else if (input[0].equalsIgnoreCase("deadline")) {
            taskType = TypeOfTask.DEADLINE;
        } else if (input[0].equalsIgnoreCase("event")) {
            taskType = TypeOfTask.EVENT;
        }

        try {
            switch (taskType) {
            case TODO:
                startIdx = 1;
                endIdx = input.length;
                description = stringConverter(input, startIdx, endIdx);
                if (description.isBlank()) {
                    throw new DukeException(input[0]);
                }

                task = new Todo(description);
                break;
            case DEADLINE:
                startIdx = 1;
                endIdx = Arrays.asList(input).indexOf("/by");
                description = stringConverter(input, startIdx, endIdx);
                if (description.isBlank()) {
                    throw new DukeException(input[0]);
                }

                startIdx = endIdx + 1;
                endIdx = input.length;
                by = stringConverter(input, startIdx, endIdx);

                task = new Deadline(description, by);
                break;
            case EVENT:
                startIdx = 1;
                endIdx = Arrays.asList(input).indexOf("/from");
                description = stringConverter(input, startIdx, endIdx);
                if (description.isBlank()) {
                    throw new DukeException(input[0]);
                }

                startIdx = endIdx + 1;
                endIdx = Arrays.asList(input).indexOf("/to");
                from = stringConverter(input, startIdx, endIdx);

                startIdx = endIdx + 1;
                endIdx = input.length;
                to = stringConverter(input, startIdx, endIdx);

                task = new Event(description, from, to);
                break;
            default:
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }

        listOfTasks.add(task);
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    public static void listTasks() {
        Task task;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            System.out.println("\t" + i + "." + task);
        }
        printLine();
    }

    public static void markTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            printLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static void unmarkTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsUndone();
            printLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static void deleteTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            printLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + task);
            System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static String stringConverter(String[] arr, int startIdx, int endIdx) {
        StringBuilder strBuild = new StringBuilder();
        String str;
        for (int i = startIdx; i < endIdx; i++) {
            strBuild.append(arr[i]);
            if (i == endIdx - 1) {
                break;
            }
            strBuild.append(" ");
        }
        str = strBuild.toString();
        return str;
    }

    public static void main(String[] args) throws DukeException {
        Scanner input = new Scanner(System.in);
        String command, firstWord;
        greet();
        while (true) {
            command = input.nextLine().trim();
            firstWord = command.split(" ")[0];
            if (command.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (command.equalsIgnoreCase("list")) {
                listTasks();
            } else if (firstWord.equalsIgnoreCase("mark")) {
                markTask(command);
            } else if (firstWord.equalsIgnoreCase("unmark")) {
                unmarkTask(command);
            } else if (firstWord.equalsIgnoreCase("delete")) {
                deleteTask(command);
            } else {
                saveTask(command);
            }
        }
        input.close();
    }
}
