import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    ————————————————————————————————");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ————————————————————————————————");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            System.out.println("    ————————————————————————————————");
            if (command.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ————————————————————————————————");
                break;
            }
            System.out.println("    " + command);
            System.out.println("    ————————————————————————————————");
        }
        sc.close();
    }
}
