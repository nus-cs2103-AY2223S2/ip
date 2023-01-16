import java.util.Scanner;

// A chatbot
public class Duke {

    private static final String EXIT_COMMAND = "bye";
    private static final String DISPLAY_LIST_COMMAND = "list";
    private static final String MARK_TASK_AS_DONE_COMMAND = "mark";
    private static final String MARK_TASK_AS_UNDONE_COMMAND = "unmark";

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
        String[] parts = input.split(" ");
        String command = parts[0];
        int taskNum;
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
            default:
                addTaskToList(input);
        }
    }

    private static void addTaskToList(String text) {
        taskList.addTask(text);
        printMessage("added: " + text);
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
