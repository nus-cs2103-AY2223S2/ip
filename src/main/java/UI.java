package main.java;

import main.java.Command.CommandType;
import java.util.Scanner;

public class UI {
    private static final String FULL_LINE = "_______________________________________________\n";
    private static final String WELCOME_TEXT = "Hello I'm Duke, your personal task manager!";
    private static final String BYE_TEXT = "Bye. Hope to see you again soon!";
    private static final String LOADING_ERROR_TEXT = "Unable to load tasks from database!";
    private TaskList taskList;
    private Scanner sc;

    public UI() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        printFormattedOutput(WELCOME_TEXT);
    }

    public void exit() {
        printFormattedOutput(BYE_TEXT);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showLoadingError() {
        printFormattedOutput(LOADING_ERROR_TEXT);
    }

    public void printFormattedOutput(String output) {
        output = FULL_LINE + output + "\n" + FULL_LINE;
        System.out.println(output);
    }

}
