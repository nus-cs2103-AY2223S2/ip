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
            } else if (command.startsWith("mark ") || command.startsWith("unmark ")) {
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
            }
            // Create new ToDo task
            else if (command.startsWith("todo ")) {
                ToDo newToDo = new ToDo(command.substring(command.indexOf(" ") + 1));
                taskList.add(newToDo);
                System.out.println("I've added this task to your list:");
                System.out.println(newToDo);
            }
            // Create new Deadline task
            else if (command.startsWith("deadline ")) {
                Deadline newDeadline = new Deadline(command.substring(command.indexOf(" ") + 1, command.indexOf("/")),
                        command.substring(command.indexOf("/by") + 4));
                taskList.add(newDeadline);
                System.out.println("I've added this task to your list:");
                System.out.println(newDeadline);
            }
            // Create new Event task
            else if (command.startsWith("event ")) {
                String from = command.substring(command.indexOf("/from") + 6, command.indexOf(" /to"));
                String to = command.substring(command.indexOf("/to") + 4);
                Event newEvent = new Event(command.substring(command.indexOf(" ") + 1, command.indexOf("/from")), from, to);
                taskList.add(newEvent);
                System.out.println("I've added this task to your list:");
                System.out.println(newEvent);
            }

            System.out.println("You have " + taskList.size() + " tasks in your list.");
        }
    }

    // Method to items to list
    private static String adder(String userInput) {
        return "added: " + userInput;
    }
}
