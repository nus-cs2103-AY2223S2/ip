import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static List<String> backlog = new ArrayList<>();
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        System.out.println("Hello! I am Duke Nice To Meet You\n");
        boolean ongoing = true;

        while(ongoing) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye! Hope to See You Again!");
                break;
            }

            if (command.equals("list")) {
                int index = 1;
                for (String logs : backlog) {
                    System.out.println(index + "." + logs);
                    index++;
                }
                System.out.println("");
                continue;
            }

            backlog.add(command);
            System.out.println("added: " + command + "\n");
        }
    }
}
