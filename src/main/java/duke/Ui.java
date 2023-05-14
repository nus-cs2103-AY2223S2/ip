package duke;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * The user interface of the program.
 */
public class Ui {

    private Parser parser;

    public Ui() {

    }
    public Ui(TaskStorage tasks) {
        this.parser = new Parser(tasks);
    }

    /**
     * Displays the welcome sign.
     */
    public static String showWelcome() {
        StringBuilder chunkOfText = new StringBuilder();
        chunkOfText.append(showLine());
        chunkOfText.append("\n");
        chunkOfText.append("Hiii Im Duke\n");
        chunkOfText.append("What can I do for you hmm?\n");
        chunkOfText.append(showLine());
        return chunkOfText.toString();
    }

    /**
     * Displays a long line for visual effects.
     */
    public static String showLine() {
        String line = "-------------------------------";
        return line;
    }

    /**
     * Lets the user know when the input they made is invalid.
     */
    public void showLoadingError() {
        System.out.println("OHOH the input cannot make it!");
    }

    /**
     * Returns the input the user typed in.
     *
     * @return The input the user typed in.
     * @throws IOException When the user type in invalid input.
     */
    public String readCommand() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
            StringBuilder sb = new StringBuilder();
            String inp;
            inp = br.readLine();
            return inp;
        } catch (IOException e) {
            return e.getMessage();
        }

    }

}
