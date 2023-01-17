import java.io.*;
import java.util.*;
public class Duke {
    private static final String logo = " |          ______    ______   \n"
                                     + " | ____    |      |  |      |  \n"
                                     + " |      |  |      |  |      |  \n"
                                     + " | ____ |  |______|  |______|  \n";
    private static final String straightLine = "_________________________________________________________________";


    public static void main(String[] args) {
        //Stores user input
        ArrayList<String> commandStorage = new ArrayList<String>();

        printIntroductoryMessage();

        //Prepare input and output sources
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //Keep taking in user input line by line
        while (true) {
            String input;
            try {
                input = br.readLine();
            }
            catch (IOException ie) {
                pw.println("Error encountered in receiving input.");
                pw.println("The error message is: ");
                pw.flush();
                ie.printStackTrace();
                break;
            }

            //User typed in "bye".
            if (input.equals("bye")) {
                break;
            }
            //User typed in "list"
            else if (input.equals("list")) {
                printUserCommands(commandStorage);
            }
            //User did not type in "bye" or "list". Store the text.
            else {
                addUserCommand(input, commandStorage);
            }
        }

        //Exits the bot with an exit message
        printExitMessage();
        pw.close();
    }


    /**
     * Prints the introductory message.
     */
    public static void printIntroductoryMessage() {
        System.out.println(logo);
        System.out.println(straightLine);
        System.out.println("Boo! Nice to meet you.");
        System.out.println("I am here to scare all your problems away.");
        System.out.println("What can I help you with today?");
        System.out.println(straightLine);
    }

    /**
     * Prints the exit message.
     */
    public static void printExitMessage() {
        System.out.println(straightLine);
        System.out.println("Goodbye. Hope that I have managed to scare all your problems away.");
        System.out.println("Have a great day! :)");
        System.out.println(straightLine);
    }

    /**
     * Prints out all the user commands that have been entered by the user thus far.
     * @param commandStorage The ArrayList that stores the user commands to be printed out.
     */
    public static void printUserCommands(ArrayList<String> commandStorage) {
        System.out.println(straightLine);
        int numberOfCommands = commandStorage.size();
        //Process each command in the storage
        for (int i = 0; i < numberOfCommands; i = i + 1) {
            String numbering = Integer.toString(i + 1) + ". ";
            String output = numbering + commandStorage.get(i);
            System.out.println(output);
        }
        System.out.println(straightLine);
    }

    /**
     * Adds user command into storage and informs the user.
     * @param command The command to be added to storage.
     * @param commandStorage The ArrayList that stores the command.
     */
    public static void addUserCommand(String command, ArrayList<String> commandStorage) {
        commandStorage.add(command);
        System.out.println(straightLine);
        System.out.println("added: " + command);
        System.out.println(straightLine);
    }





}
