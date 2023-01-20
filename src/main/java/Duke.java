import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String helpStr = "What can I help you with?";
        String byeStr = "Bye. Hope to see you again soon!";

        // create a Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // loop until "bye"
        while (true) {
            // read user input
            String command = input.nextLine();

            // check if user inputs "bye"
            if (command.equalsIgnoreCase("bye")) {
                System.out.println(byeStr);
                return;
            }
            // check if user inputs "list" and display items in list
            else if (command.equalsIgnoreCase("list")) {
                if (taskList.size() == 0) {
                    System.out.println("Your list is empty!");
                } else {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                }
            }
            else{
                // add user input to list and echo
                taskList.add(command);
                System.out.println(adder(command));
            }
        }
    }

    // Method to items to list
    private static String adder(String userInput) {
        return "added: " + userInput;
    }
}
