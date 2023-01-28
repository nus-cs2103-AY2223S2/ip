import java.util.Scanner;

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

        while (true) {
            command = sc.nextLine().toLowerCase();
            if (command.compareTo("bye") != 0) {
                System.out.println(line + indent + command + "\n" + line);
            } else {
                System.out.println(line + indent + "Bye. Hope to see you again soon!\n" + line);
                break;
            }
        }
    }
}
