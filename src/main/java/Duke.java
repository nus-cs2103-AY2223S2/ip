import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hi! I am Duke. How may I help you today?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!\n");
                return;
            }
            System.out.println(cmd + "\n");
        }
    }
}
