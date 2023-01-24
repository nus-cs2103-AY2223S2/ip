import java.util.Scanner;

public class Ui {
    private static final String spacesPrefix = "     ";
    private static final String dashes = "    "
            + "______________________________"
            + "______________________________";

    private final Scanner in;
    // private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public String getUserCommand() {
        System.out.println("Enter your desired action: ");
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showWelcome() {
        String welcomeMsg = spacesPrefix
                + "Hello! I'm Duke\n"
                + spacesPrefix
                + "How can I help you?";
        System.out.println(welcomeMsg);
    }

    public void showGoodbye() {
        String exitMsg = spacesPrefix
                + "Farewell! See you soon!";
        System.out.println(exitMsg);
    }

    public void showLoadingError() {
        String loadingErrorString = "Failed to load from file!";
        System.out.println(loadingErrorString);
    }

    public void showTaskAdded(Task task) {
        System.out.println(formatMessage("New task added: "));
        System.out.println(spacesPrefix + task);
    }

    public void showTaskRemoved(Task task) {
        System.out.println(formatMessage("Removed task: "));
        System.out.println(spacesPrefix + task);
    }

    public void showNumTasks(TaskList taskList) {
        System.out.println(spacesPrefix
                + " Now list has " + taskList.getSize() + " tasks.");
    }

    public void showList(TaskList taskList) {
        System.out.println(spacesPrefix
                + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            int number = i + 1;
            System.out.println(spacesPrefix + " "
                    + number + ". "
                    + taskList.getTaskList().get(i));
        }
    }

    public void showTaskMarked(Task task) {
        System.out.println(formatMessage("Marked task as done:"));
        System.out.println(spacesPrefix + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(formatMessage("Marked task as undone:"));
        System.out.println(spacesPrefix + task);
    }
    public void showError(String errorMessage) {
        System.out.println(formatMessage(errorMessage));
    }

    public void showLine() {
        System.out.println(dashes);
    }

    public String formatMessage(String message) {
        return spacesPrefix + message;
    }
}
