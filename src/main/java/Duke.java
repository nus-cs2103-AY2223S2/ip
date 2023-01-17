import java.util.Scanner;

// A chatbot
public class Duke {

    private static final String EXIT_COMMAND = "bye";
    private static final String DISPLAY_LIST_COMMAND = "list";
    private static final String MARK_TASK_AS_DONE_COMMAND = "mark";
    private static final String MARK_TASK_AS_UNDONE_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";

    private static Scanner scanner = new Scanner(System.in);
    private static TaskList taskList = new TaskList();

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
        return input.equals(EXIT_COMMAND);
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
        int taskNum;
        String[] textAndDate;
        try {
            switch (command) {
                case DISPLAY_LIST_COMMAND:
                    displayTasks();
                    break;
                case MARK_TASK_AS_DONE_COMMAND:
                    markTaskAsDone(parts[1]);
                    break;
                case MARK_TASK_AS_UNDONE_COMMAND:
                    markTaskAsNotDone(parts[1]);
                    break;
                case TODO_COMMAND:
                    addTodoToList(parts[1]);
                    break;
                case DEADLINE_COMMAND:
                    textAndDate = parts[1].split(" /by ");
                    addDeadlineToList(textAndDate);
                    break;
                case EVENT_COMMAND:
                    textAndDate = parts[1].split(" /from | /to ");
                    addEventToList(textAndDate);
                    break;
                default:
                    throw new InvalidCommandDukeException();
            }
        } catch (DukeException e) {
            printMessage(e.toString());
        }

    }

    private static void addTodoToList(String description) {
        Task task = new ToDoTask(description);
        taskList.add(task);
        printMessage("added: " + task);
    }

    private static void addDeadlineToList(String[] textAndDate) {
        Task task = new DeadlineTask(textAndDate[0], textAndDate[1]);
        taskList.add(task);
        printMessage("added: " + task);
    }

    private static void addEventToList(String[] textAndDate) {
        Task task = new EventTask(textAndDate[0], textAndDate[1], textAndDate[2]);
        taskList.add(task);
        printMessage("added: " + task);
    }

    private static void displayTasks() {
        printMessage("Your tasks are:\n" + taskList.toString());
    }

    private static void markTaskAsDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            if (!taskList.isValidNumber(number)) {
                throw new InvalidArgumentDukeException();
            }
            taskList.markTaskAsDone(number);
            printMessage("Good job. You have finished this task:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException e) {
            throw new InvalidArgumentDukeException();
        }
    }

    private static void markTaskAsNotDone(String arguments) throws InvalidArgumentDukeException {
        try {
            int number = Integer.parseInt(arguments);
            if (!taskList.isValidNumber(number)) {
                throw new InvalidArgumentDukeException();
            }
            taskList.markTaskAsNotDone(number);
            printMessage("Ok. I have marked this task as not done:\n"
                    + taskList.getTaskString(number)
            );
        } catch (NumberFormatException e) {
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
