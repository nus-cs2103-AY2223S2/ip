import java.io.*;
import java.util.*;

public class Duke {
    private static final String line = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        printer("Hello! I'm Duke\n      What can I do for grades?");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            printer(input);
        }
        sc.close();
    }

    private static void printer(String toPrint) {
        System.out.println("    " + line);
        System.out.println("      " + toPrint);
        System.out.println("    " + line);
        System.out.println();
    }
}
