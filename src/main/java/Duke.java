import java.util.Scanner;
public class Duke {

    /**
    Helper method to format response to user input
     */
    private static String respond(String input) {
        return "added: " + input;
    }
    public static void main(String[] args) {
        String welcomeMsg = "Hello i'm Duke\nWhat can I do for you?";
        String goodbyeMsg = "Bye. Hope to see you again soon!";
        System.out.println(welcomeMsg);
        TaskList tasks = new TaskList(); // create a list for all the tasks
        Scanner scanner = new Scanner(System.in); // creates a scanner object
        while (true) {
            String echo = scanner.nextLine(); // get user input
            if (echo.equals("bye")) {
                System.out.println(goodbyeMsg);
                return;
            } else if (echo.equals("list")) {
                System.out.println(tasks.listTasks()); // list all the stored tasks
            } else {
                Task task = new Task(echo); // convert into a task
                tasks.addTask(task);
                System.out.println(respond(task.getDescription()));
            }
        }
    }
}
