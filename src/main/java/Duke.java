import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> taskStorage = new ArrayList<Task>();

    public static void main(String[] args) {
        greetings();

        while (true) {
            String input = getUserInput();

            if (input.equals("bye"))
                break;

            if (input.contains("list")) {
                listItem();
                continue;
            }

            if (input.contains("unmark")) {
                String[] inputSplit = input.split(" ");

                unmarkItem(inputSplit[1]);
                continue;
            }

            if (input.contains("mark")) {
                String[] inputSplit = input.split(" ");
                markItem(inputSplit[1]);
                continue;
            }

            // Else store item
            storeItem(input);
        }

        bye();
    }

    public static void greetings() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static String getUserInput() {
        String userInput = in.nextLine();
        
        return userInput;
    }

    public static void storeItem(String input) {
        String[] inputSplit = input.split(" ");
        String command = inputSplit[0];
        Task task;

        switch (command) {
            case "todo":
                task = createTodo(inputSplit);
                break;
            case "deadline":
                task = createDeadline(inputSplit);
                break;
            case "event":
                task = createEvent(inputSplit);
                break;
            default:
                return;
        }

        taskStorage.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Duke.taskStorage.size() + " tasks in the list.\n");
    }

    public static void listItem() {
        int size = taskStorage.size();
        
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + taskStorage.get(i));
        
        System.out.println();
    }

    public static void markItem(String item) {
        int index = Integer.parseInt(item) - 1;
        if (index >= taskStorage.size())
            return;
        Task task = taskStorage.get(index);
        task.markAsDone();

        System.out.println(String.format("Nice! I've marked this task as done:\n%s\n", task));
    }

    public static void unmarkItem(String item) {
        int index = Integer.parseInt(item) - 1;
        if (index >= taskStorage.size())
            return;
        Task task = taskStorage.get(index);
        task.markAsNotDone();

        System.out.println(String.format("OK, I've marked this task as not done yet:\n%s\n", task));
    }

    public static Task createTodo (String[] inputSplit) {
        String taskInfo = getTaskInfo(inputSplit).strip();

        return new Todo(taskInfo);
    }

    public static Task createDeadline (String[] inputSplit) {
        String[] taskInfo = getTaskInfo(inputSplit).split("/by");
        String description = taskInfo[0].strip();
        String by = taskInfo[1].strip();

        return new Deadline(description, by);
    }

    public static Task createEvent (String[] inputSplit) {
        String[] taskInfo = getTaskInfo(inputSplit).split("/from");
        String description = taskInfo[0].strip();
        String[] timing = taskInfo[1].split("/to");
        String from = timing[0].strip();
        String to = timing[1].strip();

        return new Event(description, from, to);
    }

    private static String getTaskInfo(String[] inputSplit) {
        String[] removedCommand = Arrays.copyOfRange(inputSplit, 1, inputSplit.length);
        String taskInfo = String.join(" ", removedCommand);

        return taskInfo;
    }
}
