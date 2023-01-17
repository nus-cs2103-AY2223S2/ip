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
                    System.out.println(i+1 + ". [" + store[i].getStatusIcon() + "] " + store[i].description);
                }
            }
            else if (userInput.contains("mark")) {
                // obtaining index to mark, -1 because array starts from index 0
                int index = Integer.valueOf(userInput.substring(userInput.length() - 1)) - 1;

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
                System.out.println("[" + store[index].getStatusIcon() + "] " + store[index].description);
            }

            // handle errors out of range
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
