import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean run = true;
        String line = "    ____________________________________________________________\n";

        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String greet = line +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________";

        System.out.println("    Hello from\n" + logo);
        System.out.println(greet);

        while(run) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(line +
                        "    Bye. Hope to see you again soon!\n" +
                        line);
                run = false;
            } else {
                System.out.print(line +
                        "    " + command + "\n" +
                        line);
            }
        }
    }
}
