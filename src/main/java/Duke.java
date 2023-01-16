import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);

        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________" +
                "\n     Hello! I'm Duke" +
                "\n     What can I do for you?" +
                "\n    ____________________________________________________________");

        

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.print("     ");
            System.out.print(input);
            System.out.println("\n    ____________________________________________________________");
            input = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
        sc.close();

    }
}
