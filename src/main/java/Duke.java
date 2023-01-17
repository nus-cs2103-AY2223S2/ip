import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    protected static String indent = "     ";
    protected static String divider = indent + "____________________________________________________________";
    protected static ArrayList<String> tasks =  new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                break;
            } else if (command.equals("list")) {
                System.out.println(formatMessage(listTasks()));
            } else {
                tasks.add(command);
                System.out.println(formatMessage("added: " + command));
            }
        }
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println(indent + "Hello! I'm Duke");
        System.out.println(indent + "What can I do for you?");
        System.out.println(divider);
    }

    public static String formatMessage(String message) {
        return divider + "\n" + indent + message + "\n" + divider;
    }

    public static String listTasks() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i != 0) {
                output += indent;
            }
            output += (i + 1) + ". " + tasks.get(i);
            if (i < tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }
}
