import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // store: storing text entered by user
        Task[] store;
        store = new Task[100];

        // keeping count the next available index of store
        int counter = 0;

        // greetings
        System.out.println("Hello I'm Duke\n" + "What can I do for you?");

        // obtaining first input by user
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();

        // exit loop when user input is bye
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i ++) {
                    System.out.print(i + 1);
                    System.out.println(". " + store[i].toString());
                }
            }
            else if (userInput.contains("todo")) {
                store[counter] = new ToDo(userInput.substring(5, userInput.length()));


                System.out.println("Got it. I've added this task:");
                System.out.println(store[counter].toString());
                int display = counter + 1; // array index + 1
                System.out.println("Now you have " + display + " tasks in the list.");
                counter = counter + 1;
            }
            else if (userInput.contains("deadline")) {
                String trimmed = userInput.substring(9, userInput.length());
                String description = trimmed.substring(0, trimmed.indexOf("/") - 1);
                String deadline = trimmed.substring((trimmed.indexOf("by")) + 3, trimmed.length());
                store[counter] = new Deadline(description, deadline);


                System.out.println("Got it. I've added this task:");
                System.out.println(store[counter].toString());
                int display = counter + 1; // array index + 1
                System.out.println("Now you have " + display + " tasks in the list.");
                counter = counter + 1;
            }
            else if (userInput.contains("event")) {
                String trimmed = userInput.substring(6, userInput.length());
                String description = trimmed.substring(0, trimmed.indexOf("/from") - 1);
                String from = trimmed.substring(trimmed.indexOf("/from") + 6, trimmed.indexOf("/to") - 1);
                String to = trimmed.substring(trimmed.indexOf("/to") + 4, trimmed.length());
                store[counter] = new Event(description, from, to);

                System.out.println("Got it. I've added this task:");
                System.out.println(store[counter].toString());
                int display = counter + 1; // array index + 1
                System.out.println("Now you have " + display + " tasks in the list.");
                counter = counter + 1;
            }
            else if (userInput.contains("mark")) {
                // obtaining index to mark, -1 because array starts from index 0
                int index = Integer.valueOf(userInput.substring(userInput.length() - 1)) - 1;
                // handle errors out of range
                if (index < 0 || index >= counter) {
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
                store[counter] = new Task(userInput);
                counter = counter + 1;
                System.out.println("added: " + userInput);
            }
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }

        // prints exist statement
        System.out.println("Bye. Hope to see you again soon!");
    }
}
