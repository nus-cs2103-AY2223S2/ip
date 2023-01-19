import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void prettyPrint(String text) {
        System.out.println(
            "____________________________________________________________"
        );
        System.out.println(text);
        System.out.println(
            "____________________________________________________________\n"
        );
    }

    public static void main(String[] args) {
        String logo =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke.prettyPrint("Hello! I'm Duke\nWhat can I do for you?");

        // parse user input
        Scanner scanner = new Scanner(System.in);
        String command;
        ArrayList<String> list = new ArrayList<String>();

        // level 2 functionality:
        while (true) {
            // scan for user input
            command = scanner.nextLine();

            // parse cases
            if (command.equals("bye")) {
                // case: "bye"
                // exit program
                Duke.prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // case: "list"
                // construct string to be printed
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    String s = String.format("%d: %s\n", i + 1, list.get(i));
                    sb.append(s);
                }
                // pprint string
                Duke.prettyPrint(sb.toString());
            } else {
                // case: everything else
                // add item to list
                list.add(command);
                Duke.prettyPrint("added:" + command);
            }
        }
    }
}
