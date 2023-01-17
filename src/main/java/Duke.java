import java.io.*;
public class Duke {
    private static final String logo = " |          ______    ______   \n"
                                     + " | ____    |      |  |      |  \n"
                                     + " |      |  |      |  |      |  \n"
                                     + " | ____ |  |______|  |______|  \n";
    private static final String straightLine = "_________________________________________________________________";

    public static void main(String[] args) {
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
            //User did not type in "bye". Echo what the user typed.
            else {
                pw.println(straightLine);
                pw.println(input);
                pw.println(straightLine);
                pw.flush();
            }
        }

        //Exits the bot with an exit message
        printExitMessage();
        pw.close();
    }


    /**
     * Prints the introductory message
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
     * Prints the exit message
     */
    public static void printExitMessage() {
        System.out.println(straightLine);
        System.out.println("Goodbye. Hope that I have managed to scare all your problems away.");
        System.out.println("Have a great day! :)");
        System.out.println(straightLine);
    }



}
