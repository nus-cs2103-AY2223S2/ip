package DukeHelpfulCode.Utilities;

import java.util.Scanner;

import DukeHelpfulCode.Commands.*;

public class UI {
    /** Handles the user inputs and stuff
     * methods
     * showWelcome()
     * showLine() = linebreak
     * showError(Exception )
     */

    private static String LINEBREAK = "_________________________________________________________________\n";

    public void showWelcome(){
        /**
         * Prints the Logo and Welcome Message
         */
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + LINEBREAK + "Type 'bye' to exit!\n" + LINEBREAK);
    }

    public void showCommands(){
        /**
         * Prints the list of Commands
         */
        Command[] allCmd = {new HelpCommand(),
                            new AddCommand(),
                            new DeleteCommand(),
                            new MarkCommand(),
                            new ListCommand(),
                            new ExitCommand()};
        String commandList = LINEBREAK + "Here's the list of commands:\n" + LINEBREAK; // just add the tostring of the commands
        for (int i = 0; i < allCmd.length; i++){
            commandList += i+1 + ". " + allCmd[i].toString();
        }
        System.out.println(commandList);
    }

    public void showLine(){
        /**
         * Prints the LINEBREAK
         */
        System.out.println(LINEBREAK);
    }

    public void exit(){
        System.out.println(LINEBREAK + "Thanks for using DUKE. Hope you have a great day ahead!");
    }

    public void showLoadingError(){
        System.out.println("Oops, there seems to be a problem with loading your previous Task List?\n");
    }

    public void showError(String message){
        System.out.println(message);
    }

    public void showList(){}

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How may I help you today?\n");
        return sc.nextLine();
    }
}
