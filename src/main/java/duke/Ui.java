package duke;

import duke.exceptions.DukeException;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printResponse(String input) {
        String horizLine = "_____________________________";
        System.out.printf("%s\n%s\n%s\n%n", horizLine, input, horizLine);
    }

    public void welcomeMessage() {
        printResponse("Hello! I'm Interrobang\nWhat can I do for you today?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(DukeException e) {
        printResponse(e.getMessage());
    }

    public void sayBye() {
        printResponse("Bye! Hope to see you again soon!");
    }
}
