import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        boolean loop = true;
        while (loop) {
            Scanner echoScanner = new Scanner(System.in);
            String msg = echoScanner.nextLine();
            String bye = "bye";
            if (!bye.equalsIgnoreCase(msg)) {
                System.out.println(msg);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                loop = false;
            }
        }
    }
}
