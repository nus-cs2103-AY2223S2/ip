import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) throws DukeException {
        // store: storing text entered by user
        ArrayList<Task> store = new ArrayList<Task>();

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
                    System.out.println(". " + store.get(i).toString());
                }
            }
            else if (userInput.contains("todo")) {
                try {
                    store.add(Task.noOfTasks, new ToDo(userInput.substring(5, userInput.length())));
                } catch(Exception e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            }
            else if (userInput.contains("deadline")) {
                String trimmed = "";
                String description = "";
                String deadline = "";
                try {
                    trimmed = userInput.substring(9, userInput.length());
                    description = trimmed.substring(0, trimmed.indexOf("/") - 1);
                } catch(Exception e) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                try {
                    deadline = trimmed.substring((trimmed.indexOf("by")) + 3, trimmed.length());
                } catch(Exception e) {
                    throw new DukeException("The date of a deadline cannot be empty.");
                }
                store.add(Task.noOfTasks, new Deadline(description, deadline));
            }
            else if (userInput.contains("event")) {
                String trimmed = "";
                String description = "";
                String from = "";
                String to = "";
                try {
                    trimmed = userInput.substring(6, userInput.length());
                    description = trimmed.substring(0, trimmed.indexOf("/from") - 1);
                } catch (Exception e) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                try {
                    from = trimmed.substring(trimmed.indexOf("/from") + 6, trimmed.indexOf("/to") - 1);
                } catch (Exception e) {
                    throw new DukeException("The start time of a event cannot be empty.");
                }
                try {
                    to = trimmed.substring(trimmed.indexOf("/to") + 4, trimmed.length());
                } catch (Exception e) {
                    throw new DukeException("The end time of a event cannot be empty.");
                }
                store.add(Task.noOfTasks, new Event(description, from, to));
            }
            else if (userInput.contains("mark")) {
                // obtaining index to mark, -1 because array starts from index 0
                int index = Integer.valueOf(userInput.substring(userInput.indexOf("mark") + 5, userInput.length())) - 1;
                // handle errors out of range
                if (index < 0 || index >= Task.noOfTasks) {
                    int display = index + 1;
                    throw new DukeException("Task " + display + " does not exist.");
                }
                else if (userInput.contains("unmark")) {
                    store.get(index).markAsUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                } else {
                    store.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                }
                System.out.println(store.get(index).toString());
            }
            else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }

        // prints exit statement
        System.out.println("Bye. Hope to see you again soon!");
    }
}
