import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> TASK_LIST = new ArrayList<>();

    private static void addToDo(String userCommand) throws DukeException {

        String[] userCommandParts = userCommand.split(" ", 2);
        if (userCommandParts.length < 2) {
            throw new DukeInvalidArgumentException("Please enter task description");
        }

        String taskDescription = userCommandParts[1].trim();
        ToDo toDo = new ToDo(taskDescription);
        TASK_LIST.add(toDo);
        System.out.println("Got it. I've added this task:\n\t" + toDo
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
    }

    private static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("Please enter a valid datetime: dd-MM-yyyy HH:mm");
        }
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

        LocalDateTime dueDate = parseDateTime(userCommandParts[1].trim()) ;
        Deadline deadline = new Deadline(description, dueDate);
        TASK_LIST.add(deadline);

        System.out.println("Got it. I've added this task:\n\t" + deadline
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
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

        LocalDateTime startDateTime = parseDateTime(timeParts[0].trim());
        LocalDateTime endDateTime = parseDateTime(timeParts[1].trim());

        if (!endDateTime.isAfter(startDateTime)) {
            throw new DukeInvalidArgumentException("The end date/time should be after the start date/time");
        }

        Event event = new Event(description, startDateTime, endDateTime);
        TASK_LIST.add(event);

        System.out.println("Got it. I've added this task:\n\t" + event
                + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
    }

    private static void printTaskList() {
        if (TASK_LIST.size() == 0) {
            System.out.println("There are no tasks in your Task List!");
        } else {
            System.out.println("Your Tasks:");
            for (int i = 0; i < TASK_LIST.size(); i++) {
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

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                task.setDone();
                System.out.println("Task marked as completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
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

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");

            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                task.setNotDone();
                System.out.println("Task marked as not completed\n" + task);

            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }

    }
    private static void deleteTask(String userCommand) throws DukeException {
        try {
            String[] userCommandParts = userCommand.split(" ");
            if (userCommandParts.length != 2) {
                throw new DukeInvalidArgumentException("Use format: delete {task no.}");
            }

            String taskNumber = userCommandParts[1];
            int taskIndex = Integer.parseInt(taskNumber) - 1;

            if (TASK_LIST.size() == 0) {
                System.out.println("There are no tasks in your Task List!");
            } else if (0 <= taskIndex && taskIndex < TASK_LIST.size()) {
                Task task = TASK_LIST.get(taskIndex);
                TASK_LIST.remove(taskIndex);

                System.out.println("Noted. I've deleted this task:\n\t" + task
                        + "\nNow you have " + TASK_LIST.size() + " task(s) in the list.");
            } else {
                throw new DukeInvalidArgumentException("Please provide a valid Task number\n" +
                        "You have " + TASK_LIST.size() + " task(s) in your Task List");
            }

        } catch (NumberFormatException exception) {
            throw new DukeInvalidArgumentException("Please provide a valid Task number");
        }
    }

    private static void processCommand(String userCommand, Storage storage) throws DukeException {

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
            case "delete":
                deleteTask(userCommand);
                break;
            default:
                throw new DukeInvalidCommandException("beep...boop... unrecognized command!");
        }

        storage.storeTaskList(TASK_LIST);
    }

    public static void start() {
        Ui ui = new Ui();
        Storage storage = new Storage();

        ui.greet();
        TASK_LIST = storage.loadTaskList();

        // Ready for commands
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                ui.exit();
                break;
            }
            try {
                processCommand(input, storage);

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
