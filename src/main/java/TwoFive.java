import java.util.ArrayList;
import java.util.Scanner;

public class TwoFive {
    public static String taskAdded(Task task, int numTasks) {
        return "Got it. I've added this task:\n " + task + "\nNow you have " + numTasks + " tasks in the list";
    }

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
                System.out.println(" " + currentTask);
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
                System.out.println(" " + currentTask);
            } else if (input.contains("todo")){
                //Adds a new ToDo task
                String taskDescription = input.split("todo")[1].trim();
                ToDo newToDo = new ToDo(taskDescription);
                //Adds new task to list of tasks
                tasks.add(newToDo);
                System.out.println(taskAdded(newToDo, tasks.size()));
            } else if (input.contains("deadline")){
                //Adds a new Deadline task
                String taskDescription = input.split("deadline")[1].split("/by")[0].trim();
                String deadline = input.split("/by")[1].trim();
                Deadline newDeadline = new Deadline(taskDescription, deadline);
                //Adds new task to list of tasks
                tasks.add(newDeadline);
                System.out.println(taskAdded(newDeadline, tasks.size()));
            } else if (input.contains("event")){
                //Adds a new Event task
                String taskDescription = input.split("event")[1].split("/from")[0].trim();
                String startTime = input.split("/from")[1].split("/to")[0].trim();
                String endTime = input.split("/to")[1].trim();
                Event newEvent = new Event(taskDescription, startTime, endTime);
                //Adds new task to list of tasks
                tasks.add(newEvent);
                System.out.println(taskAdded(newEvent, tasks.size()));
            }

            System.out.println(divider);
            input = inputScanner.nextLine();
        }

        System.out.println(divider);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
