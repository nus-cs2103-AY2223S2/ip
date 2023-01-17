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

            switch(input) {
                //List all tasks added by the user
                case "list":
                    for (Task task: tasks) {
                        System.out.println(task);
                    }
                    break;
                //Adds a new task for any other input
                default:
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
