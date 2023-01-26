package duke.ui;

import duke.exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public Ui(){}

    public void greet() {
        printText("\t Hello! I'm Duke\n\t What can I do for you?");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void printResponse(String s) {
        printText(s);
    }

    public void printException(DukeException e) {
        printText(e.getMessage());
    }

    private void printText(String s) {
        System.out.println("\t____________________________________________________________");
        System.out.println(s);
        System.out.println("\t____________________________________________________________\n");
    }
}
