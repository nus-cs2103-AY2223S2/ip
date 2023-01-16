import java.util.*;
import java.io.*;

public class Duke {
    public static String HOR_BAR = "    ✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦✦";
    public static void initialise() {
        /* Greetings from application */
        System.out.println(HOR_BAR);
        String logo = "    ૮ ˶ᵔ ᵕ ᵔ˶ ა";
        System.out.println("    Hey there! I'm Berry the Bunny~\n" + logo + "\n"
                + "    What are you looking to plan today?");
        System.out.println(HOR_BAR);
    }

    /**
     * This method reads in a command and does the following:
     * 1. if user inputs "bye" command, prints exit message and exit
     *      the function
     * 2. else repeats the command back and loop back into function
     */
//    private static boolean handleCommand(String command) {
//
//    }

    /**
     * This method takes in a command and repeats it back.
     * @param commmand
     *          Command to be repeated back to interface.
     */
    private static void repeat(String command) {
        System.out.println(HOR_BAR + "\n    " + command + "\n" + HOR_BAR);
    }
    public static void main(String[] args) throws IOException {
        /* Initialise BufferedReader */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
           initialise();
           while (true) {
               String command = br.readLine();
               if (command.equals("bye")) {
                   System.out.println(HOR_BAR + "\n" + "    Bye! Please come back again ><!");
                   break;
               } else {
                   repeat(command);
               }
           }
        } finally {
            br.close();
        }
    }
}

