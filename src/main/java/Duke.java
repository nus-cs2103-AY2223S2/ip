import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Initialise variables
        List<String> userHistory =  new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String delimiter = "\t---";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(delimiter);
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(delimiter);
                break;
            }
            System.out.println(delimiter);
            if (command.equals("list")) {
                int i = 1;
                for (String s: userHistory) {
                    System.out.printf("\t%d. %s\n", i, s);
                    i++;
                }
            } else {
                System.out.printf("\tadded: %s\n", command);
                userHistory.add(command);
            }
            System.out.println(delimiter);
        }
    }
}
