import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        final String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String separator = "\t____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        System.out.println(separator
                + "\n\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + separator);
        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println(separator
                        + "\n\t Bye. Hope to see you again!\n"
                        + separator);
                break;
            }
            System.out.println(separator + "\n\t"
                    + command
                    + "\n"
                    + separator);
        }
    }
}
