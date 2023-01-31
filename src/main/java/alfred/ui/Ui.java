package alfred.ui;

import java.util.Scanner;

import alfred.exceptions.AlfredException;

/**
 * Represents the interface where it deals with the user interaction with the uer.
 */
public class Ui {

    private Scanner sc;
    /**
     * Constructs an Ui object where it defines how the system input is given.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }


    /**
     * Asks the user for the next input.
     * @return the input written by the user.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Displays the opening introduction by the program.
     */
    public void displayOpening() {
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        System.out.println("| Your favourite personal assistant:  |");
        System.out.println("*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*");
        displayLogo();
        displayIntro();
    }

    /**
     * Displays the command given.
     * @param command The command written by the user.
     */
    public void displayCommand(String command) {
        displayLine();
        command = "    " + command;
        System.out.println(command);
        displayLine();
    }

    /**
     * Displays the error given.
     * @param e The AlfredException that was thrown in the program.
     */
    public void displayError(AlfredException e) {
        displayLine();
        String output = "     " + e.getMessage();
        System.out.println(output);
        displayLine();
    }

    /**
     * Displays the goodbye message by the program.
     */
    public void displayBye() {
        String command = "Bye. Hope to see you again soon!";
        displayCommand(command);
    }

    /**
     * Displays the logo of the program.
     */
    public void displayLogo() {
        System.out.println(" _____ __     ______ _____ ____ ___ ");
        System.out.println("|  -  |  |   |  ____|  _  |  __| _ \\     ");
        System.out.println("| | | |  |   | |___ | |_|_| |__|| | |  ");
        System.out.println("|  -  |  |___|  ___||  _ \\  |__||_| |");
        System.out.println("|_| |_| ____ |__|   |_| \\_|____|__ /   ");
    }

    /**
     * Displays the opening message from the program.
     */
    public void displayIntro() {
        String intro = "Hello! I'm Alfred :>\n"
                + "How can I help you today?";
        displayLine();
        System.out.println(intro);
        displayLine();
    }

    /**
     * Displays an empty line that separates commands.
     */
    public void displayLine() {
        System.out.println("    ____________________________________________________________");
    }
}
