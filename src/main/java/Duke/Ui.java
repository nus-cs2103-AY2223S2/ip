package Duke;

import Duke.Exceptions.DukeException;
import Duke.Tasks.TaskList;
import java.util.Scanner;
import java.lang.String;

/**
 * Contains Ui object that deals with interactions with the user
 */
public class Ui {
    private final static String UNDERLINE = "________________________________________________________";
    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner text = new Scanner(System.in);

    /**
     * The method of Underline() to print out a separating line
     */
    public static String Underline(){
        return (UNDERLINE);
    }

    /**
     * The method of readIn user input
     * @return String
     */
    public String readIn(){
        return this.text.nextLine();
    }

    /**
     * The method of showing logo and welcoming when run at start
     */
    public void greet() {
        System.out.println(logo);
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println(Ui.Underline());
    }

    /**
     * The method of sayBye when exiting program
     */
    public static String sayBye() {
        System.out.println(Ui.Underline());
        return "Bye. Hope you never need me again!";
    }

    /**
     * The method of markedMessage()
     */
    public static String markedMessage() {
        return"\tNice! I've finally finished this mission:";

    }

    /**
     * The method of unMarkedMessage()
     */
    public static String unMarkedMessage() {
        return"\tUhh, I have not done this:";
    }

    /**
     * The method of showDeleteMessage()
     */
    public static String showDeleteMessage() {
        return "\tOkay. that's one off the list:";
    }



    /**
     * The method of listMsg()
     */
    public static String listMsg() {
        return "\tHere are the missions:";

    }

}

