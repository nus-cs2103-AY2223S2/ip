import java.io.*;
import java.util.*;

public class Duke {
    private static final String LINE_STRING = "____________________________________________________________";
    private static final String WELCOME_STRING = "Hello! I'm Duke\n      What can I do for grades?";
    private static final String END_STRING = "Bye. Hope to see you again soon!";
    private static final String EMPTY_LIST_STRING = "Hey, the list is empty!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        printer(WELCOME_STRING);

        ArrayList<String> todoList = new ArrayList<String>(100);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            boolean end = false;
            switch (input) {
                case "list":
                    if (todoList.size() == 0) {
                        printer(EMPTY_LIST_STRING);
                    } else {
                        String toPrint = "";
                        for (int i = 0; i < todoList.size(); i++) {
                            toPrint += ((i + 1) + ". " + todoList.get(i)) + "\n      ";
                        }
                        printer(toPrint.substring(0, toPrint.length() - 8));
                    }
                    break;
                case "bye":
                    end = true;
                    printer(END_STRING);
                    break;

                default:
                    todoList.add(input);
                    printer("added: " + input);
                    break;
            }
            if (end) {
                break;
            }
        }
        sc.close();
    }

    private static void printer(String toPrint) {
        System.out.println("    " + LINE_STRING);
        System.out.println("      " + toPrint);
        System.out.println("    " + LINE_STRING);
        System.out.println();
    }
}
