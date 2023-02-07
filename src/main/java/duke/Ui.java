package duke;

import java.util.Scanner;

public class Ui {

    public Ui() {

    }

    /**
     * Takes input from the user and returns it as String.
     * @param sc Scanner object to take user input.
     * @return User input as String.
     */
    public String takeInput(Scanner sc) {
        String input = sc.nextLine();
        if (input.equals("bye")) {
            return "bye";
        } else {
            return input;
        }
    }

    /**
     * Prints greeting message for Duke.
     */
    public void sayHello() {
        System.out.println(Duke.logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
    }

    /**
     * Prints closing message for Duke.
     */
    public void sayBye() {
        System.out.printf(Duke.DIV_OPEN + "Bye. Hope to see you again soon!\n"+ Duke.DIV_CLOSE);
    }

    /**
     * Prints error message from loading.
     */
    public void showLoadingError() {
        System.out.println("Error occurred while loading file! Is the datafile missing or corrupted?");
    }


}
