package duke.io.input.ui;


import java.util.Scanner;

import duke.util.Storage;
import duke.workflow.Event;
import duke.workflow.Greeting;

/**
 * Initiates Duke's interaction workflow with use.
 * The flow starts with the {@code Greeting} event where Duke
 * says hello to the user.
 *
 * <p> It then proceeds to the {@code DoTask} event
 * where Duke makes sense of the user input and determine the possible
 * actions. </p>
 *
 * <p> The workflow ends with the {@code Ending} event where Duke
 * says goodbye to the user. </p>
 *
 */

public class ChatBot {
    private boolean usedByUser;
    private Event lastEvent;

    /**
     * Constructor of the chatBot class
     *
     */

    public ChatBot () {
        this.usedByUser = false;
        this.lastEvent = new Greeting();
    }

    /**
     * Greet the user when he opens Duke
     *
     */

    public void greetUser() {
        UserInterface.printLogo();
    }


    /**
     * Duke begins interacting with user.
     * Get user input to determine whether he wants to run Duke.
     * Loop the part of code to get user input until a valid input is found.
     * Print warning when user input is invalid.
     *
     */

    public void beginChat() {
        Scanner sc = new Scanner(System.in);
        Greeting greeting = new Greeting();
        System.out.println(UserInterface.printWithBracket(greeting.toString()));
        String isUsingDuke = sc.nextLine();

        while (!isUsingDuke.equals("YES") && !isUsingDuke.equals("NO")) {
            UserInterface.printInputWarning();
            isUsingDuke = sc.nextLine();
        }

        if (isUsingDuke.equals("NO")) {
            Event currentEvent = greeting.toNextEvent("NOT PLAYING");
            System.out.println(UserInterface.printWithBracket(currentEvent.toString()));
        } else {
            Event currentEvent = greeting.toNextEvent("PLAYING");
            System.out.println(UserInterface.printWithBracket(currentEvent.toString()));

            while (!currentEvent.isFinalEvent()) {
                String nextTask = sc.nextLine();
                currentEvent = currentEvent.toNextEvent(nextTask);
                System.out.println(UserInterface.printWithBracket(currentEvent.toString()));
            }
            usedByUser = true;
            lastEvent = currentEvent;
        }
    }

    /**
     * Ending Duke's interaction with user.
     * Asking whether he wants to save the current progress
     */

    public void endChat() {
        if (usedByUser) {
            Storage.saveProgressQuery(lastEvent.getTaskList());
        }
    }
}
