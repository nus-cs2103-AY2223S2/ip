import java.util.ArrayList;
import java.util.Scanner;

public class TwoFive {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        String logo = "  _______            ______ _\n"
                    + " |__   __|          |  ____(_)\n"
                    + "    | |_      _____ | |__   ___   _____\n"
                    + "    | \\ \\ /\\ / / _ \\|  __| | \\ \\ / / _ \\\n"
                    + "    | |\\ V  V / (_) | |    | |\\ V /  __/\n"
                    + "    |_| \\_/\\_/ \\___/|_|    |_| \\_/ \\___|\n";

                System.out.println("Hello from\n" + logo);

        String divider = "____________________________________________________________";

        //Greets users
        System.out.println(divider);
        System.out.println("In case you're still not clear, I'm TwoFive!");
        System.out.println("I'm your personal assistant chatbot");
        System.out.println("What can I do for you?");
        System.out.println(divider);

        //Reads input from user
        Scanner inputScanner = new Scanner(System.in);
        String input = inputScanner.nextLine();

        //Exits when user types bye
        while (!input.equals("bye")) {
            //Echos input from user
            System.out.println(divider);

            if (input.equals("list")) {
                //List all tasks added by the user
                System.out.println("Here are the tasks in your list:");
                int taskIndex = 1;
                for (Task task: tasks) {
                    System.out.println(taskIndex + ". " + task);
                    taskIndex++;
                }
            } else if (input.contains("unmark")) {
                //Marks selected task as undone
                Integer taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currentTask = tasks.get(taskNum);
                boolean unmarkSuccess = currentTask.markAsUndone();
                if (unmarkSuccess) {
                    System.out.println("OK, I've marked this task as not done yet:");
                } else {
                    System.out.println("Oops, this task has not been done yet:");
                }
                System.out.println(currentTask);
            } else if (input.contains("mark")) {
                //Marks selected task as done
                Integer taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currentTask = tasks.get(taskNum);
                boolean markSuccess = currentTask.markAsDone();
                if (markSuccess) {
                    System.out.println("Nice! Congrats for completing this task:");
                } else {
                    System.out.println("Oops, this task is already done:");
                }
                System.out.println(currentTask);
            } else {
                //Adds a new task for any other input
                Task newTask = new Task(input);
                //Adds new task to list of tasks
                tasks.add(newTask);
                System.out.println("added: " + input);
            }

            System.out.println(divider);
            input = inputScanner.nextLine();
        }

        System.out.println(divider);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
