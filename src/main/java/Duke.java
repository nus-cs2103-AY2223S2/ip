import java.util.Scanner;


public class Duke {

    private static String lineBreak = "_________________________________________________________________\n";

    public static void main(String[] args) {

        greeting();
        takeCmd();

    }

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "Type 'bye' to exit!\n" + lineBreak + "What can I do for you today?\n");
    }

    public static void takeCmd() {
        Scanner userCmd = new Scanner(System.in);
        String cmd = userCmd.nextLine();
        if (cmd.equals("bye")) {
            exit();
        } else {
            System.out.println(lineBreak + cmd + "\n" + lineBreak);
            takeCmd();
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!\n" + lineBreak);
    }

}
