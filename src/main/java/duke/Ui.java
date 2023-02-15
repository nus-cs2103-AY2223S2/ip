package duke;

import java.util.Scanner;

/**
 * Handles all interactions with the user
 */
public class Ui {
    private final String WELCOME_MESSAGE = "Hey Buddy, I'm Duke\nWhat can I do for you?";
    private final String GOODBYE_MESSAGE = "Goodbye friend. Hope to see you again soon!";
    private Scanner scanner;

    /**
     * Creates a new UI object to interact with the user
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message to the user upon starting
     */
    public void welcomeUser() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Reads the input from the user through the interface
     *
     * @return the String input from the user
     */
    public String readInput() {
        System.out.println("=======ME=======");
        String textInput = this.scanner.nextLine();
        return textInput;
    }

    /**
     * Prints the goodbye message to the user upon termination
     */
    public void goodbyeUser() {
        System.out.println("======DUKE======");
        System.out.println(GOODBYE_MESSAGE);
        this.scanner.close();
        System.out.println("================");
    }

    /**
     * Prints all the tasks inside a TaskList object
     *
     * @param taskList the TaskList object to be listed to the interface
     */
    public void listTasks(TaskList taskList) {
        System.out.println("======DUKE======");
        System.out.println(taskList.toString());
    }

    /**
     * Reads an input and prints it as a response
     *
     * @param response the response to be printed
     */
    public void printResponse(String response) {
        System.out.println("======DUKE======");
        System.out.println(response);
    }
}