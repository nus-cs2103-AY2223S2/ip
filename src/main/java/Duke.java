import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // Duke's greeting
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        // Take user input
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String currentTask = sc.nextLine();

            // Command for bye
            if (currentTask.equals("bye")) {
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(goodbye);
                break;
            }

            // Command for list
            else if (currentTask.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int taskCount = 1;
                for(Task t: taskList) {
                    System.out.println(taskCount + "." + t);
                    taskCount++;
                }
            }

            // Add task command
            else {
                System.out.println("added: " + currentTask);
                taskList.add(new Task(currentTask));
            }
        }
    }
}
