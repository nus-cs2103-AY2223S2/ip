import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> taskStorage = new ArrayList<Task>();

    public static void main(String[] args) {
        greetings();

        while (true) {
            try {    
                String input = getUserInput();
                String command = input.split(" ")[0].toLowerCase();
                
                if (command.equals("bye")) {
                    bye();
                    break;
                }

                switch (command) {
                    case "list":
                        listItem();
                        break;
                    case "unmark":
                        unmarkItem(input);
                        break;
                    case "mark":
                        markItem(input);
                        break;
                    case "todo":
                    case "deadline":
                    case "event":
                        Task task = processTaskInput(input);
                        storeItem(task);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error);
            }
        }

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

    public static void listItem() {
        int size = taskStorage.size();
        
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + taskStorage.get(i));
        
        System.out.println();
    }

    public static void markItem(String item) throws MarkIndexDoesNotExistException {
        if (item.split(" ").length == 1)
            throw new  MarkIndexDoesNotExistException("☹ OOPS!!! missing mark index");
        item = item.split(" ")[1];
        int index = Integer.parseInt(item) - 1;
        if (index >= taskStorage.size())
            throw new MarkIndexDoesNotExistException("☹ OOPS!!! mark index does not exist");
        Task task = taskStorage.get(index);
        task.markAsDone();

        System.out.println(String.format("Nice! I've marked this task as done:\n%s\n", task));
    }

    public static void unmarkItem(String item) throws UnmarkIndexDoesNotExistException {
        if (item.split(" ").length == 1)
            throw new  UnmarkIndexDoesNotExistException("☹ OOPS!!! missing unmark index");
        item = item.split(" ")[1];
        int index = Integer.parseInt(item) - 1;
        if (index >= taskStorage.size())
            throw new UnmarkIndexDoesNotExistException("☹ OOPS!!! unmark index does not exist");
        Task task = taskStorage.get(index);
        task.markAsNotDone();

        System.out.println(String.format("OK, I've marked this task as not done yet:\n%s\n", task));
    }

    public static Task processTaskInput(String input) throws DukeException {
        String[] inputSplit = input.split(" ");
        String command = inputSplit[0];
        Task task = null;
        String taskInfo = getTaskInfo(inputSplit);

        switch (command) {
            case "todo":
                task = createTodo(taskInfo);
                break;
            case "deadline":
                task = createDeadline(taskInfo);
                break;
            case "event":
                task = createEvent(taskInfo);
                break;
        }

        return task;
    }

    public static void storeItem(Task task) {
        taskStorage.add(task);
    
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Duke.taskStorage.size() + " tasks in the list.\n");
    }

    public static Task createTodo (String taskInfo) throws EmptyTaskArgumentException {
        if (taskInfo.isEmpty())
            throw new EmptyTaskArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");

            String description = taskInfo.strip();

        return new Todo(description);
    }

    public static Task createDeadline (String taskInfo) throws EmptyTaskArgumentException {
        String[] taskInfoArray = taskInfo.split("/by");

        if (taskInfo.isEmpty())
            throw new EmptyTaskArgumentException("☹ OOPS!!! The description of a deadline cannot be empty.");
        if (!taskInfo.contains("/by"))
            throw new EmptyTaskArgumentException("☹ OOPS!!! deadline requires a /by.");
        if (taskInfoArray.length != 2)
            throw new EmptyTaskArgumentException("☹ OOPS!!! The /by of a deadline cannot be empty.");

        String description = taskInfoArray[0].strip();
        String by = taskInfoArray[1].strip();

        return new Deadline(description, by);
    }

    public static Task createEvent (String taskInfo) throws EmptyTaskArgumentException {
        String[] taskInfoArray = taskInfo.split("/from");
        String[] timing = taskInfoArray[1].split("/to");

        if (taskInfo.isEmpty())
            throw new EmptyTaskArgumentException("☹ OOPS!!! The description of a event cannot be empty.");
        if (!taskInfo.contains("/from"))
            throw new EmptyTaskArgumentException("☹ OOPS!!! event requires a /from.");
        if (!taskInfo.contains("/to"))
            throw new EmptyTaskArgumentException("☹ OOPS!!! event requires a /to.");
        if (taskInfo.indexOf("/to") < taskInfo.indexOf("/from"))
            throw new EmptyTaskArgumentException("☹ OOPS!!! wrong format. It should be event <DESCRIPTION> /from <FROM> /to <TO>");
        if (taskInfoArray.length != 2)
            throw new EmptyTaskArgumentException("☹ OOPS!!! The /from of a deadline cannot be empty.");
        if (timing.length != 2)
            throw new EmptyTaskArgumentException("☹ OOPS!!! The /to of a deadline cannot be empty.");

        String description = taskInfoArray[0].strip();
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
