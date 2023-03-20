package DukeHelpfulCode.Utilities;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import DukeHelpfulCode.Commands.*;

public class UI {
    /** Handles the user inputs and stuff
     * methods
     * showWelcome()
     * showLine() = linebreak
     * showError(Exception )
     */

    private static String LINEBREAK = "=================================\n";
    private static String convertFromUtf8ToIso(String s1) {
        if(s1 == null) {
            return null;
        }
        String s = new String(s1.getBytes(StandardCharsets.UTF_8));
        byte[] b = s.getBytes(StandardCharsets.ISO_8859_1);
        return new String(b, StandardCharsets.ISO_8859_1);
    }

    public String intro = "Hello from DOOK, your own tasks manager (for irl tasks, not computer ones)\n"
            + LINEBREAK;

    public String showCommands(){
        /**
         * Prints the list of commands.
         *
         * @param   none
         * @return  none
         */
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
        return commandList;
    }

    public String showLine(){
        /**
         * Prints Linebreak.
         *
         * @param   none
         * @return  none
         */
        return LINEBREAK;
    }

    public String exit(){
        /**
         * Exits DOOK.
         *
         * @param   none
         * @return  none
         */
        return LINEBREAK + "Thanks for using DOOK. Hope you have a great day ahead!";
    }

    public String showLoadingError(){
        return "Oops, there seems to be a problem with loading your previous Task List?\n";
    }

    public String showError(String message){
        /**
         * Prints the error message
         *
         * @param   message The error message to be displayed.
         * @return  none
         */
        return message;
    }

    public void showList(){}

    public String readCommand() {
        /**
         * Reads the user Command.
         *
         * @params  none
         * @return  command The command that the user inputs.
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("How may I help you today?\n");
        return sc.nextLine();
    }
}
