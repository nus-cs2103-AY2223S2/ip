import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");

        System.out.println("\t____________________________________________________________");
        TaskList taskList = new TaskList();
        Commands commands = new Commands(taskList);

        String userInput = sc.nextLine().strip();
        while (!userInput.equals("bye")) {
            String taskType = userInput.split(" ")[0];
            if (taskType.equals("list")) {
                commands.listTasks();
            } else if (taskType.equals("mark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                commands.markTask(taskId);
            } else if (taskType.equals("unmark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                commands.unmarkTask(taskId);
            } else if (taskType.equals("todo")) {
                commands.addToDoTask(userInput);
            } else if (taskType.equals("deadline")) {
                commands.addDeadlineTask(userInput);
            } else if (taskType.equals("event")) {
                commands.addEventTask(userInput);
            }
            userInput = sc.nextLine().strip();

        }
        sc.close();
        System.out.println("\t____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }
}
