import java.util.Scanner;

public class Duke {
    private static void printMsg(String[] msgs) {
        System.out.println("____________________________________________________________");
        for (String msg :  msgs) {
            System.out.println(msg);
        }
        System.out.println("____________________________________________________________");
    }

    private static void printMsg(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String[] welcomeMsg = {logo, "Hello I am Duke", "What can I do for you?"};
        printMsg(welcomeMsg);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.next();
            if (cmd.equals("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            }
            printMsg(cmd);
        }
    }
}
