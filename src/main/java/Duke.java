import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);
        System.out.println("    ____________________________________________________________" +
                "\n     Hello! I'm Duke" +
                "\n     What can I do for you?" +
                "\n    ____________________________________________________________");

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            switch (input) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println("     " + i + ". " + tasks.get(i));
                    }
                    System.out.println("    ____________________________________________________________");
                    break;
                default:
                    tasks.add(input);
                    String output = "    ____________________________________________________________"
                            + "\n      added: "
                            + input
                            + "\n    ____________________________________________________________";
                    System.out.println(output);
                    break;
            }
            input = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");

        sc.close();

    }
}
