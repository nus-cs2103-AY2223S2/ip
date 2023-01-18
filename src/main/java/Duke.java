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

            else if (userInput.split(" ", 2)[0].equals("todo")) {
                try {
                    store.add(Task.noOfTasks, new ToDo(userInput.split(" ", 2)[0]));
                } catch(Exception e) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            }
            else if (userInput.split(" ", 2)[0].equals("deadline")) {
                try {
                    String[] input = userInput.split(" ", 2)[1].split(" /by ", 2);
                    store.add(Task.noOfTasks, new Deadline(input[0], input[1]));
                } catch(Exception e) {
                    throw new DukeException("The description and date of a deadline cannot be empty.");
                }
            }
            else if (userInput.split(" ", 2)[0].equals("event")) {
                try {
                    String[] input = userInput.split(" ", 2)[1].split(" /", 3);
                    store.add(Task.noOfTasks, new Event(input[0], input[1], input[2]));
                } catch (Exception e) {
                    throw new DukeException("The description, start time, and end time of a event cannot be empty.");
                }
            }
            else if (userInput.contains("mark")) {
                // obtaining index to mark, -1 because array starts from index 0
                int index = Integer.valueOf(userInput.substring(userInput.indexOf("mark") + 5)) - 1;
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
            else if (userInput.contains("delete")) {
                int index = Integer.valueOf(userInput.substring(userInput.indexOf("delete") + 7)) - 1;
                // handle errors out of range
                if (index < 0 || index >= Task.noOfTasks) {
                    int display = index + 1;
                    throw new DukeException("Task " + display + " does not exist.");
                }
                else {
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(store.get(index).toString());
                    store.get(index).remove();
                    store.remove(index);
                    System.out.println("Now you have " + Task.noOfTasks + " tasks in the list.");
                }
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
