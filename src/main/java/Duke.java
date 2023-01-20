import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String helpStr = "What can I help you with?";
        String byeStr = "Bye. Hope to see you again soon!";

        System.out.println(helpStr);
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
            else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
                // Mark task as done
                if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(command.replaceAll("mark ", "")) - 1;
                    taskList.get(index).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(index));
                }
                // Unmark task
                else if (command.substring(0, command.indexOf(" ")).equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(command.replaceAll("unmark ", "")) - 1;
                    taskList.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList.get(index));
                }
                //return;
            }

            else{
                // add user input to list and echo
                Task newTask = new Task(command);
                taskList.add(newTask);
                System.out.println(adder(command));
            }
        }
    }

    // Method to items to list
    private static String adder(String userInput) {
        return "added: " + userInput;
    }
}
