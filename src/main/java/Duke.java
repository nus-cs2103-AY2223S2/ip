import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class Duke {
    private static final List<Task> tasks = new ArrayList<>();
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

        while (parseCommand(sc.nextLine().trim()));

        return;
    }

    /**
     * Takes in a command and attempts to perform it, if valid.
     * Accepted commands: [add], todo, deadline, mark, unmark, list, bye
     * [add] is invoked whenever any string that does not match other commands is entered.
     * @param   command a string containing the command entered by the user
     * @return          true if programme should continue accepting further commands, else false
     */
    private static boolean parseCommand(String command) {
        String[] args = command.split(" ");
        switch (args[0]) {
            case "bye":
                prettyPrint("Hope I helped. Goodbye!");
                return false;
            case "mark":
                // todo: check if second argument is a valid number
                prettyPrint("Great job! I've marked this task as done: ");
                tasks.get(Integer.parseInt(args[1]) - 1).complete();
                prettyPrint(tasks.get(Integer.parseInt(args[1]) - 1).toString());
                return true;
            case "unmark":
                // todo: check if second argument is a valid number
                prettyPrint("Aww... I've marked this task as not done yet: ");
                tasks.get(Integer.parseInt(args[1]) - 1).uncomplete();
                prettyPrint(tasks.get(Integer.parseInt(args[1]) - 1).toString());
                return true;
            case "todo":
            case "deadline":
                prettyPrint("Got it! I've added this task:");
                if (args[0].equals("todo")) {
                    tasks.add(new ToDo(String.join(" ", Arrays.copyOfRange(args, 1, args.length))));
                } else if (args[0].equals("deadline")) {
                    // todo: check if '/by' exists
                    int byIndex = command.indexOf("/by ");

                    // startIndex of command.substring() is 9 as "deadline " is 9 chars long
                    tasks.add(new Deadline(
                            command.substring(9, byIndex).trim(),
                            command.substring(byIndex + 4, command.length()).trim()));
                }
                prettyPrint(tasks.get(tasks.size() - 1).toString());
                prettyPrint(String.format("Now you have %d task%s in the list.",
                        tasks.size(), tasks.size() > 1 ? "s" : ""));
                return true;
            case "list":
                for (int i = 1; i <= tasks.size(); i++) {
                    prettyPrint(String.format("%d. %s", i, tasks.get(i - 1)));
                }
                return true;
            default:
                tasks.add(new Task(command));
                prettyPrint("added: " + command);
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
