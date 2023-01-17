import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        // store: storing text entered by user
        Task[] store;
        store = new Task[100];

        // greetings
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        // obtaining first input by user
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();

        // exit loop when user input is bye
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < Task.noOfTasks; i ++) {
                    System.out.print(i + 1);
                    System.out.println(". " + store[i].toString());
                }
            }
            else if (userInput.contains("todo")) {
                store[Task.noOfTasks] = new ToDo(userInput.substring(5, userInput.length()));

            }
            else if (userInput.contains("deadline")) {
                String trimmed = userInput.substring(9, userInput.length());
                String description = trimmed.substring(0, trimmed.indexOf("/") - 1);
                String deadline = trimmed.substring((trimmed.indexOf("by")) + 3, trimmed.length());
                store[Task.noOfTasks] = new Deadline(description, deadline);
            }
            else if (userInput.contains("event")) {
                String trimmed = userInput.substring(6, userInput.length());
                String description = trimmed.substring(0, trimmed.indexOf("/from") - 1);
                String from = trimmed.substring(trimmed.indexOf("/from") + 6, trimmed.indexOf("/to") - 1);
                String to = trimmed.substring(trimmed.indexOf("/to") + 4, trimmed.length());
                store[Task.noOfTasks] = new Event(description, from, to);
            }
            else if (userInput.contains("mark")) {
                // obtaining index to mark, -1 because array starts from index 0
                int index = Integer.valueOf(userInput.substring(userInput.indexOf("mark") + 5, userInput.length())) - 1;
                // handle errors out of range
                if (index < 0 || index >= Task.noOfTasks) {
                    int display = index + 1;
                    System.out.println("Task " + display + " does not exist.");
                    myObj = new Scanner(System.in);
                    userInput = myObj.nextLine();
                    continue;
                }
                else if (userInput.contains("unmark")) {
                    store[index].markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                } else {
                    store[index].markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(store[index].toString());
            }
            else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }

        // prints exist statement
        System.out.println("Bye. Hope to see you again soon!");
    }
}
