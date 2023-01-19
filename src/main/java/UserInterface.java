import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {
    private InputStream in;
    private PrintStream out;
    private Scanner sc;

    UserInterface() {
        in = System.in;
        out = System.out;
        sc = new Scanner(in);
    }

    private void printLine() {
        out.println("____________________________________________________________");
    }

    public void showGreeting() {
        printLine();
        out.println();
        out.println("Hello! I'm Duke");
        out.println("What can I do for you?");
        printLine();
    }

    public void showExitMessage() {
        showMessage("Bye! Have a great day!");
    }

    public void showMessage(String message) {
        out.println();
        out.println(message);
        printLine();
    }

    public void showPrompt() {
        out.print("\n> ");
    }

    public String getInput() {
        // if (!sc.hasNextLine()) return "";
        showPrompt();
        String input = sc.nextLine();
        return input;
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public void showTasks(TaskList list) {
        String message = list.stream().map(t -> String.format("%d. %s", t.id(), t.toString()))
                .collect(Collectors.joining("\n"));

        showMessage(message);
    }
}
