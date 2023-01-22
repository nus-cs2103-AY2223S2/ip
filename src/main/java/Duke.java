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

    private static void addToDo(String userCommand) {
        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            System.out.println("Please enter task description");
            return;
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASK_LIST.add(toDo);
        nTasks++;
        System.out.println("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addDeadline(String userCommand) {

        String[] userCommandParts = userCommand.split(" /by", 2);
        String description = userCommandParts[0].replace("deadline", "").trim();

        if (description.isEmpty()) {
            System.out.println("Please enter task description!");
            return;
        }

        if (userCommandParts.length < 2) {
            System.out.println("Invalid format!");
            System.out.println("Use `deadline {description} /by {due date}`");
            return;
        }

        if (userCommandParts[1].trim().isEmpty()) {
            System.out.println("Please provide due date after `/by` parameter");
            return;
        }

        String dueDate = userCommandParts[1].trim();
        Deadline deadline = new Deadline(description, dueDate);
        TASK_LIST.add(deadline);
        nTasks++;

        System.out.println("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addEvent(String userCommand) {

        String[] userCommandParts = userCommand.split(" /from", 2);
        String description = userCommandParts[0].replace("event", "").trim();

        if (description.isEmpty()) {
            System.out.println("Please enter task description!");
            return;
        }

        if (userCommandParts.length < 2) {
            System.out.println("Invalid format!");
            System.out.println("Use `event {description} /from {start date/time} /to {end date/time}`");
            return;
        }

        String[] timeParts = userCommandParts[1].split(" /to", 2);

        if (timeParts.length < 1 || timeParts[0].trim().isEmpty()) {
            System.out.println("Please provide start date/time after `/from` parameter");
            return;
        }

        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            System.out.println("Please provide end date/time after `/to` parameter");
            return;
        }

        String startDateTime = timeParts[0].trim();
        String endDateTime = timeParts[1].trim();

        Event event = new Event(description, startDateTime, endDateTime);
        TASK_LIST.add(event);
        nTasks++;

        System.out.println("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void printTaskList() {
        if (nTasks == 0) {
            System.out.println("There are no tasks in your Task List!");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < nTasks; i++) {
                System.out.println((i + 1) + ". " + TASK_LIST.get(i));
            }
        }
        System.out.println();
    }

    private static void markTask(String userCommand) {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                System.out.println("Use format: mark {task no.}");
                return;
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (nTasks == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setDone();
            } else {
                System.out.println("Please provide a valid Task number");
                System.out.println("You have " + nTasks + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            System.out.println("Please provide a valid Task number");
        }
    }

    private static void unmarkTask(String userCommand) {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                System.out.println("Use format: unmark {task no.}");
                return;
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (nTasks == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setNotDone();
            } else {
                System.out.println("Please provide a valid Task number");
                System.out.println("You have " + nTasks + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            System.out.println("Please provide a valid Task number");
        }

    }

    private static void processCommand(String userCommand) {

        if (userCommand.isEmpty()) {
            System.out.println("Please enter a command");
            return;
        }

        String command = userCommand.split(" ", 2)[0];

        // Single word commands
        if (userCommand.equals("list")) {
            printTaskList();
            return;
        }

        // Multi-word commands
        switch (command) {
            case "todo":
                addToDo(userCommand);
                break;
            case "deadline":
                addDeadline(userCommand);
                break;
            case "event":
                addEvent(userCommand);
                break;
            case "mark":
                markTask(userCommand);
                break;
            case "unmark":
                unmarkTask(userCommand);
                break;
            default:
                System.out.println("beep...boop... unrecognized command!");
                break;
        }

        System.out.print("\n");
    }

    public static void start() {
        Duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
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
