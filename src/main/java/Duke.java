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
        String command = scanner.nextLine();

        // If input != bye, echo input
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                int taskCount = 1;
                for(String str: taskList) {
                    System.out.println(taskCount + ". " + str);
                    taskCount++;
                }
            } else {
                System.out.println("added: " + command);
                taskList.add(command);
            }
            command = scanner.nextLine();
        }

        if (command.equals("bye")) {
            String goodbye = "Bye. Hope to see you again soon!";
            System.out.println(goodbye);
        }
    }
}
