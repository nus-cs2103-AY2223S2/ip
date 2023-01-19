import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {

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
            try {
                if (taskType.equals("list")) {
                    commands.listTasks();
                } else if (taskType.equals("mark")) {
                    commands.markTask(userInput);
                } else if (taskType.equals("unmark")) {
                    commands.unmarkTask(userInput);
                } else if (taskType.equals("delete")) {
                    commands.deleteTask(userInput);
                } else if (taskType.equals("todo")) {
                    commands.addToDoTask(userInput);
                } else if (taskType.equals("deadline")) {
                    commands.addDeadlineTask(userInput);
                } else if (taskType.equals("event")) {
                    commands.addEventTask(userInput);
                } else {
                    System.out.println("\t____________________________________________________________");
                    System.out.println("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("\t____________________________________________________________");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                userInput = sc.nextLine().strip();
            }
        }

        sc.close();
        System.out.println("\t____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");

    }
}
