
import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    public static void main(String[] args) {
        String greetings = "Heyyo, Pandora at your service \n"
                + "What can I do for you?";
        System.out.println(greetings);

        //Initial inputs
        String userMessage;

        //To-do-list
        ArrayList<Task> toDoList = new ArrayList<Task>();

        while (true) {
            Scanner userInput = new Scanner(System.in);
            userMessage = userInput.nextLine();
            String [] parts = userMessage.split(" ", 2);

            if (parts[0].equals("bye")) {
                System.out.print("  Cya~ Till next time!");
                break;
            } else if (parts[0].equals("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < toDoList.size(); i++) {
                    int numbering = i + 1;
                    System.out.println("    " + numbering + "." + toDoList.get(i).toString());
                }
            } else if (parts[0].equals("mark")) {
                try {
                    int selection = Integer.parseInt(parts[1]) - 1;
                    toDoList.get(selection).mark();
                    System.out.println("    OK, I've marked this task as done:");
                    System.out.println("     " + toDoList.get(selection).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! Please specify which task to mark.");
                }
            } else if (parts[0].equals("unmark")) {
                try {
                    int selection = Integer.parseInt(parts[1]) - 1;
                    toDoList.get(selection).unmark();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("     " + toDoList.get(selection).toString());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! Please specify which task to unmark.");
                }
            } else if (parts[0].equals("delete")) {
                try {
                    int selection = Integer.parseInt(parts[1]) - 1;
                    System.out.println("    Noted. I've removed this task:");
                    System.out.println("     " + toDoList.get(selection).toString());
                    toDoList.remove(selection);
                    System.out.println("    Now you have " + toDoList.size() + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! Please specify which task to delete.");
                }
            } else if (parts[0].equals("todo")){
                try {
                    Todo newToDo = new Todo(parts[1]);
                    toDoList.add(newToDo);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newToDo.toString());
                    System.out.println("    Now you have " + toDoList.size() + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! The description of todo cannot be empty.");
                }
            } else if (parts[0].equals("deadline")) {
                try {
                    String[] deadlineParts = parts[1].split("/");
                    String[] deadline = deadlineParts[1].split(" ", 2);
                    Deadline newDeadline = new Deadline(deadlineParts[0], deadline[1]);
                    toDoList.add(newDeadline);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newDeadline.toString());
                    System.out.println("    Now you have " + toDoList.size() + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! The description of deadline cannot be empty.");
                }

            } else if (parts[0].equals("event")) {
                try {
                    String[] eventParts = parts[1].split("/");
                    String[] from = eventParts[1].split(" ", 2);
                    String[] to = eventParts[2].split(" ", 2);
                    Event newEvent = new Event(eventParts[0], from[1], to[1]);
                    toDoList.add(newEvent);
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("     " + newEvent.toString());
                    System.out.println("    Now you have " + toDoList.size() + " tasks in the list");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("    OOPS!!! The description of event cannot be empty.");
                }
            } else {
                System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :<");
            }
        }
    }
}
