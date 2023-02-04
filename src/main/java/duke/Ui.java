package duke;

import java.util.Scanner;

/**
 * Represents a user interface. Main responsibility is taking input and display default interface.
 */

public class Ui {
    public Scanner scanner = new Scanner(System.in);

    public void begin() {
        showLine();

        System.out.println("Hello from duke.Duke");
        System.out.println("What can I do for you?");

        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
        scanner.close();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
