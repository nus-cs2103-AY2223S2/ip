import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // store: storing text entered by user
        String[] store;
        store = new String[100];

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
                for (int i = 0; i < counter; i ++) {
                    System.out.println(i+1 + ". " + store[i]);
                }
                myObj = new Scanner(System.in);
                userInput = myObj.nextLine();
            }
            else {
                store[counter] = userInput;
                counter = counter + 1;
                System.out.println("added: " + userInput);
                myObj = new Scanner(System.in);
                userInput = myObj.nextLine();
            }
        }

        // prints exist statement
        System.out.println("Bye. Hope to see you again soon!");
    }
}
