package Willy.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the Ui class
 */
public class Ui {

    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    /**
     * To get and return the string collected by the BufferedReader
     * @return String
     * @throws IOException
     */
    public String getCommand() throws IOException {
        String str = br.readLine();
        return str;
    }

    /**
     * Prints out the startup msg
     */
    public void showStartupMessage() {
        System.out.println("Hello! I'm Willy\nWhat can I do for you?");
    }

    /**
     * Prints out the error msg
     * @param e
     */
    public void showError(String e) {
        System.out.println(e);
    }

    /**
     * Prints out a line divider
     */
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

}
