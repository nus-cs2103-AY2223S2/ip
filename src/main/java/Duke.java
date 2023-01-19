import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final List<Task> TASK_LIST =  new ArrayList<>();
    private static int nTasks = 0;

    private static void greet() {
        String greeting = "____________________________________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(LOGO);
        System.out.println(greeting);
    }

    private static void exit() {
        String exitMessage = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(exitMessage);
    }

    private static void addToDo(String task) {
        if (task.equals("")) {
            System.out.println("Invalid command format!");
            System.out.println("Use: `todo {description}`");
            return;
        }

        ToDo toDo = new ToDo(task);
        TASK_LIST.add(toDo);
        nTasks++;
        System.out.println("Got it. I've added this task:\n" + toDo
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addDeadline(String task) {
        String[] taskParts = task.split("/by");
        if (taskParts.length < 2 || taskParts[0].equals("")) {
            System.out.println("Invalid command format!");
            System.out.println("Use `deadline {description} /by {due date}`");
            return;
        }
        String description = taskParts[0].trim();
        String dueDate = taskParts[1].trim();

        if (dueDate.equals("")) {
            System.out.println("Please enter a valid due date!");
            return;
        }

        Deadline deadline = new Deadline(description, dueDate);
        TASK_LIST.add(deadline);
        nTasks++;

        System.out.println("Got it. I've added this task:\n" + deadline
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addEvent(String task) {
        String[] taskParts = task.split("/from");
        if (taskParts.length < 2 || taskParts[1].equals("")) {
            System.out.println("Invalid command format!");
            System.out.println("Use `event {description} /from {start date/time} /to {end date/time}`");
            return;
        }
        String[] taskTimeParts = taskParts[1].split("/to");
        if (taskTimeParts.length < 2) {
            System.out.println("Invalid command format!");
            System.out.println("Use `event {description} /from {start date/time} /to {end date/time}`");
            return;
        }

        String description = taskParts[0].trim();
        String startDateTime = taskTimeParts[0].trim();
        String endDateTime = taskTimeParts[1].trim();

        if (description.equals("") || startDateTime.equals("") || endDateTime.equals("")) {
            System.out.println("Please enter valid description/ start time/ end time!");
            return;
        }

        Event event = new Event(description, startDateTime, endDateTime);
        TASK_LIST.add(event);
        nTasks++;

        System.out.println("Got it. I've added this task:\n" + event
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addTask(String userCommand) {
        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            System.out.println("Invalid command format!");
            System.out.println("Use: `{todo/deadline/event} {description} {...args}`");
            return;
        }

        String command = userCommandParts[0];
        String description = userCommandParts[1];

        switch (command) {
            case "todo":
                addToDo(description);
                break;
            case "deadline":
                addDeadline(description);
                break;
            case "event":
                addEvent(description);
                break;
            default:
                System.out.println("Please enter a valid command");
                break;
        }
    }

    private static void printTaskList() {
        System.out.println("Your Tasks:");
        for (int i = 0; i < nTasks; i++) {
            System.out.println((i + 1) + ". " + TASK_LIST.get(i));
        }
    }

    private static void processCommand(String userCommand) {

        String[] userCommandParts = userCommand.split(" ");
        String command = userCommandParts[0];

        if (userCommand.equals("")) {
            System.out.println("Please enter a valid command");

        } else if (userCommand.equals("list")) {
            printTaskList();

        } else if (command.equals("mark") && userCommandParts.length == 2) {
            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1; // handle NumberFormatException
            if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setDone();
            }

        } else if (command.equals("unmark") && userCommandParts.length == 2) {
            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1; // handle NumberFormatException
            if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setNotDone();
            }

        } else {
            addTask(userCommand);
        }

        System.out.print("\n");
    }

    public static void start() {
        Duke.greet();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Duke.exit();
                break;
            }
            processCommand(input);
        }
    }

    public static void main(String[] args) {
        start();
    }
}
