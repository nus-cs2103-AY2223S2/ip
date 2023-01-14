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

        String[] data = new String[100];

        System.out.println("    Hello from\n" + logo);
        System.out.println(greet);
        int id = 0;

        while(run) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(line +
                        "    Bye. Hope to see you again soon!\n" +
                        line);
                run = false;
            } else if (command.equals("list")) {
                System.out.print(line);
                for (int i = 0; i < id; i++) {
                    System.out.printf("    %d. %s%n", i + 1, data[i]);
                }
                System.out.print(line);
            } else {
                System.out.print(line +
                        "    added: " + command + "\n" +
                        line);
                data[id] = command;
                id++;
            }
        }
    }
}
