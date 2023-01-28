import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "    __________________________________\n";
        String indent = "      ";
        String logo = indent + " ____        _        \n"
                + indent + "|  _ \\ _   _| | _____ \n"
                + indent + "| | | | | | | |/ / _ \\\n"
                + indent + "| |_| | |_| |   <  __/\n"
                + indent + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(line + indent + "Hello! I'm\n" + logo);
        System.out.println(indent + "What can I do for you?\n" + line);

        Scanner sc = new Scanner(System.in);
        String command;
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            command = sc.nextLine().toLowerCase();
            if (command.compareTo("bye") == 0) {
                System.out.println(line + indent + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (command.compareTo("list") == 0) {
                System.out.println(line);
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(indent + i + ". " + tasks.get(i-1));
                }
                System.out.println(line);
            } else {
                tasks.add(command);
                System.out.println(line + indent + "added: " + command + "\n" + line);
            }
        }
    }
}
