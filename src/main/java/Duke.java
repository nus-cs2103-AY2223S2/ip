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

    private static void addToDo(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Please enter task description");
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASK_LIST.add(toDo);
        nTasks++;
        System.out.println("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addDeadline(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /by", 2);
        String description = userCommandParts[0].replace("deadline", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n" +
                    "Use `deadline {description} /by {due date}`");
        }

        if (userCommandParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide due date after `/by` parameter");
        }

        String dueDate = userCommandParts[1].trim();
        Deadline deadline = new Deadline(description, dueDate);
        TASK_LIST.add(deadline);
        nTasks++;

        System.out.println("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + nTasks + " task(s) in the list.");
    }

    private static void addEvent(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" /from", 2);
        String description = userCommandParts[0].replace("event", "").trim();

        if (description.isEmpty()) {
            throw new DukeInvalidArgumentException("Please enter task description!");
        }

        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Invalid format!\n" +
                    "Use `event {description} /from {start date/time} /to {end date/time}`");
        }

        String[] timeParts = userCommandParts[1].split(" /to", 2);

        if (timeParts.length < 1 || timeParts[0].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide start date/time after `/from` parameter");
        }

        if (timeParts.length < 2 || timeParts[1].trim().isEmpty()) {
            throw new DukeInvalidArgumentException("Please provide end date/time after `/to` parameter");
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
    }

    private static void markTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: mark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (nTasks == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setDone();
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + nTasks + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    private static void unmarkTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: unmark {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (nTasks == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < nTasks) {
                TASK_LIST.get(taskIndex).setNotDone();
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + nTasks + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }

    }

    private static void processCommand(String userCommand) throws DukeException {

        if (userCommand.isEmpty()) {
            throw new DukeInvalidCommandException("Please enter a command");
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
                throw new DukeInvalidCommandException("beep...boop... unrecognized command!");
        }
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
            try {
                processCommand(input);
            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }

            System.out.println();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        start();
    }
}
