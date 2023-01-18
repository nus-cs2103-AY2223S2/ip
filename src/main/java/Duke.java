import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    private static final List<String> toDoList = new ArrayList<>();
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final String logo = "   _____  _  _                      \n" +
                "  / ____|| |(_)                     \n" +
                " | |     | | _  _ __   _ __   _   _ \n" +
                " | |     | || || '_ \\ | '_ \\ | | | |\n" +
                " | |____ | || || |_) || |_) || |_| |\n" +
                "  \\_____||_||_|| .__/ | .__/  \\__, |\n" +
                "               | |    | |      __/ |\n" +
                "               |_|    |_|     |___/ ";
        System.out.println("Hello from\n" + logo);
        prettyPrint("Hello! I'm Clippy, your lightweight personal assistant.");
        prettyPrint("What can I do for you today?");

        while (parseCommand(sc.nextLine()));

        return;
    }

    /**
     * Takes in a command and attempts to perform it, if valid.
     * Accepted commands: [add], list, bye
     * [add] is invoked whenever any string that does not match other commands is entered.
     * @param   command a string containing the command entered by the user
     * @return          true if programme should continue accepting further commands, else false
     */
    private static boolean parseCommand(String command) {
        switch (command) {
            case "bye":
                prettyPrint("Hope I helped. Goodbye!");
                return false;
            case "list":
                for (int i = 1; i <= toDoList.size(); i++) {
                    prettyPrint(String.format("%d. %s", i, toDoList.get(i - 1)));
                }
                return true;
            default:
                prettyPrint(command);
                return true;
        }
    }

    /**
     * Prints out the specified string with a prepended ">>> ".
     * @param output the output to be printed
     */
    private static void prettyPrint(String output) {
        System.out.println(">>> " + output);
    }
}
