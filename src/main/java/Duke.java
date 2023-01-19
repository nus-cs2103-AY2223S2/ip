
import java.util.Scanner;

public class Duke {

    public static void Level1() {
        System.out.println( "____________________________________________________________\n"
            + " Hello! I'm Duke\n"
            + " What can I do for you?\n"
            + "____________________________________________________________\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println( "____________________________________________________________\n"
                    + command
                    + "\n____________________________________________________________\n");
        }

        System.out.println( "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Level1();
    }
}
