import java.util.ArrayList;
import java.util.Arrays;
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

    public static void saveTask(String command) {
        int start_idx, end_idx;
        Task task;
        String description, by, from, to;
        String[] input = command.split(" ");

        if (input[0].equalsIgnoreCase("todo")) {
            start_idx = 1;
            end_idx = input.length;
            description = stringConverter(input, start_idx, end_idx);

            task = new Todo(description);

        } else if (input[0].equalsIgnoreCase("deadline")) {
            start_idx = 1;
            end_idx = Arrays.asList(input).indexOf("/by");
            description = stringConverter(input, start_idx, end_idx);

            start_idx = end_idx + 1;
            end_idx = input.length;
            by = stringConverter(input, start_idx, end_idx);

            task = new Deadline(description, by);

        } else {
            start_idx = 1;
            end_idx = Arrays.asList(input).indexOf("/from");
            description = stringConverter(input, start_idx, end_idx);

            start_idx = end_idx + 1;
            end_idx = Arrays.asList(input).indexOf("/to");
            from = stringConverter(input, start_idx, end_idx);

            start_idx = end_idx + 1;
            end_idx = input.length;
            to = stringConverter(input, start_idx, end_idx);

            task = new Event(description, from, to);

        }

        listOfTasks.add(task);
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task.toString());
        System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    public static void listTasks() {
        Task task;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            System.out.println("\t" + i + "." + task.toString());
        }
        printLine();
    }

    public static void markTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        Task task = listOfTasks.get(index - 1);
        task.markAsDone();
        printLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  " + task.toString());
        printLine();
    }

    public static void unmarkTask(String command) {
        int index = Integer.parseInt(command.split(" ")[1]);
        Task task = listOfTasks.get(index - 1);
        task.markAsUndone();
        printLine();
        System.out.println("\tOK, I've marked this task as not done yet:");
        System.out.println("\t  " + task.toString());
        printLine();
    }

    public static String stringConverter(String[] arr, int start_idx, int end_idx) {
        StringBuilder str_build = new StringBuilder();
        String str;
        for (int i = start_idx; i < end_idx; i++) {
            str_build.append(arr[i]);
            if (i == end_idx - 1) { break; }
            str_build.append(" ");
        }
        str = str_build.toString();
        return str;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int index;
        String command, firstWord;
        greet();
        while (true) {
            command = input.nextLine();
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
            } else {
                saveTask(command);
            }
        }
        input.close();
    }
}
