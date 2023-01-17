import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(" What can I do for you?");

        String input = "";
        ArrayList<String> tasksList = new ArrayList<String>();

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int taskNumber = 1;
                for (String task : tasksList) {
                    System.out.println(Integer.toString(taskNumber) + ". " + task);
                    taskNumber++;
                }
            } else {
                tasksList.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
