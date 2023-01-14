import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Duke's greeting
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        // Take user input
        List<String> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String currCommand = scanner.nextLine();

            // Command for bye
            if (currCommand.equals("bye")) {
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(goodbye);
                break;
            }

            // Command for list
            else if (currCommand.equals("list")) {
                int taskCount = 1;
                for(String str: taskList) {
                    System.out.println(taskCount + ". " + str);
                    taskCount++;
                }
            }

            // Add task command
            else {
                System.out.println("added: " + currCommand);
                taskList.add(currCommand);
            }
        }
    }
}
