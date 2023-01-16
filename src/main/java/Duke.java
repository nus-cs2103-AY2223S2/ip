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
        switch (command) {
            case DISPLAY_LIST_COMMAND:
                displayTasks();
                break;
            case MARK_TASK_AS_DONE_COMMAND:
                taskNum = Integer.parseInt(parts[1]);
                markTaskAsDone(taskNum);
                break;
            case MARK_TASK_AS_UNDONE_COMMAND:
                taskNum = Integer.parseInt(parts[1]);
                markTaskAsNotDone(taskNum);
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
                printMessage("Invalid command.");
        }
    }

    private static void addTodoToList(String description) {
        Task task = taskList.addTodo(description);
        printMessage("added: " + task);
    }

    private static void addDeadlineToList(String[] textAndDate) {
        Task task = taskList.addDeadline(textAndDate);
        printMessage("added: " + task);
    }

    private static void addEventToList(String[] textAndDate) {
        Task task = taskList.addEvent(textAndDate);
        printMessage("added: " + task);
    }

    private static void displayTasks() {
        printMessage("Your tasks are:\n" + taskList.toString());
    }

    private static void markTaskAsDone(int number) {
        taskList.markTaskAsDone(number);
        printMessage("Good job. You have finished this task:\n"
                        + taskList.getTaskString(number)
                );
    }

    private static void markTaskAsNotDone(int number) {
        taskList.markTaskAsNotDone(number);
        printMessage("Ok. I have marked this task as not done:\n"
                + taskList.getTaskString(number)
        );
    }

    // Main method
    public static void main(String[] args) {
        greet();
        acceptCommands();
        sayGoodbye();
    }
}
