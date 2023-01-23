import java.time.format.DateTimeParseException;
import java.util.Scanner;

// A chatbot
public class Duke {

    private static Scanner scanner = new Scanner(System.in);
    private static TaskList taskList = new TaskList();
    private static Parser parser = new Parser();

    private static String formatMessage(String message) {
        String FORMAT_LINE = "___________________________";
        return FORMAT_LINE + "\n"
                + message + "\n"
                + FORMAT_LINE;
    }

    private static void printMessage(String message) {
        System.out.println(formatMessage(message));
    }

    private static String getInputFromUser() {
        return scanner.nextLine();
    }

    private static void printPromptForInput() {
        System.out.print(">");
    }

    private static void greet() {
        printMessage("Hello, I am Duke.\n"
                + "What can I do for you?");
    }

    private static void sayGoodbye() {
        printMessage("Goodbye. I hope to see you again.");
    }

    private static boolean isExitCommand(String input) {
        return input.equals(CommandType.EXIT.getCommand());
    }

    // Loop for user input
    private static void acceptCommands() {
        String input;
        while (true) {
            printPromptForInput();
            input = getInputFromUser();
            if (isExitCommand(input)) {
                return;
            }
            executeOneCommand(input);
        }
    }

    // Executes a command, except exit command
    private static void executeOneCommand(String input) {
        // Split into two parts at the first space
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        CommandType commandType;
        try {
            commandType = CommandType.getCommandType(command);
            if (commandType.hasArguments()) {
                executeCommandWithArgument(commandType, parts);
            } else {
                executeCommandWithNoArgument(commandType);
            }
        } catch (DukeException e) {
            printMessage(e.toString());
        }

    }

    public static void executeCommandWithArgument(CommandType command, String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new EmptyArgumentDukeException();
        }
        switch (command) {
            case MARK_TASK_AS_DONE:
                markTaskAsDone(parts[1]);
                break;
            case MARK_TASK_AS_UNDONE:
                markTaskAsNotDone(parts[1]);
                break;
            case TODO:
                addTodoToList(parts[1]);
                break;
            case DEADLINE:
                addDeadlineToList(parts[1]);
                break;
            case EVENT:
                addEventToList(parts[1]);
                break;
            case DELETE:
                deleteTask(parts[1]);
                break;
        }
    }

    public static void executeCommandWithNoArgument(CommandType command) {
        switch (command) {
            case DISPLAY_LIST:
                displayTasks();
                break;
        }
    }

    private static void addTodoToList(String description) {
        Task task = new ToDoTask(description);
        taskList.add(task);
        printMessage("added: " + task);
    }

    private static void addDeadlineToList(String arguments) throws DukeException {
        try {
            String[] splitArgs = arguments.split(" /by ");
            Task task = new DeadlineTask(splitArgs[0], parser.parseDateTime(splitArgs[1]));
            taskList.add(task);
            printMessage("added: " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentDukeException();
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private static void addEventToList(String arguments) throws DukeException {
        try {
            String[] splitArgs = arguments.split(" /from ");
            String[] times = splitArgs[1].split(" /to ");
            Task task = new EventTask(splitArgs[0], parser.parseDateTime(times[0]), parser.parseDateTime(times[1]));
            taskList.add(task);
            printMessage("added: " + task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyArgumentDukeException();
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private static boolean isStringSplit(String[] split, String original) {
        return split[0].length() != original.length();
    }

    private static void displayTasks() {
        printMessage("Your tasks are:\n" + taskList.toString());
    }

    private static void markTaskAsDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            taskList.markTaskAsDone(number);
            printMessage("Good job. You have finished this task:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private static void markTaskAsNotDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            taskList.markTaskAsNotDone(number);
            printMessage("Ok. I have marked this task as not done:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private static void deleteTask(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            String taskString = taskList.getTaskString(number);
            taskList.remove(number);
            printMessage("Ok. I have deleted this task:\n"
                    + taskString
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    // Main method
    public static void main(String[] args) {
        greet();
        acceptCommands();
        sayGoodbye();
    }
}
