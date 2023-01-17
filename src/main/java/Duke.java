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
            System.out.println(userInput);
            myObj = new Scanner(System.in);
            userInput = myObj.nextLine();
        }

        // prints exist statement
        System.out.println("Bye. Hope to see you again soon!");
    }
}
