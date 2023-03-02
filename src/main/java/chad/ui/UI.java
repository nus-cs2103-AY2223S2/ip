package chad.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * duke.ui.UI to print the response after performing action.
 */
public class UI {

    private final String logo = "██████   █████  ██████   █████  \n"
            + "██   ██ ██   ██ ██   ██ ██   ██ \n"
            + "██   ██ ██   ██ ██████  ███████ \n"
            + "██   ██ ██   ██ ██   ██ ██   ██ \n"
            + "██████   █████  ██   ██ ██   ██ \n\n";
    private final String intro = "Chadbot at your service \n";
    private final String icebreaker = "What can I do for you?";
    private final String breakLine = "________________________________________________________________";
    private final String border = "\n================================================================\n";
    private BufferedReader br;

    /**
     * Constructor to instantiate new UI.
     */
    public UI() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Ui to read input for user.
     * @return User's input.
     * @throws IOException
     */
    public String readInput() throws IOException {
        return br.readLine();
    }

    /**
     * Prints response
     * @param res duke.Duke's response(String) to print
     */
    public void printRes(String res) {
        System.out.println(border + res + border);
    }

    /**
     * Greet the user
     */
    public void greet() {
        System.out.println(intro + logo + icebreaker);
        System.out.println(breakLine);
        System.out.println();
    }

    /**
     * Echo the user's response
     */
    public void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            printRes(input);
            input = sc.nextLine();
        }

        sc.close();
    }

    /**
     * Exit the program with an outro
     */
    public void exit() {
        String outro = "bella ciao";
        printRes(outro);
    }

    /**
     * return String for introduction
     * @return Hello message: String
     */
    public static String showWelcomeMsg() {
        return "Chadbot at your service, how may I assist you, ma G";
    }
}
