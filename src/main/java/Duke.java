import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        /* project starts here */
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<String>();
        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                for (int i = 0; i < toDoList.size(); i++) {
                    System.out.println(i + 1 + ": " + toDoList.get(i));
                }
                continue;
            }
            if (command.equals("bye")) {
                break;
            }
            toDoList.add(command);
            System.out.println("Added: " + command);
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
