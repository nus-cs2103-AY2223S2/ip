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

    /**
     * Displays the welcome sign.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.showLine();
        System.out.println("Hiii Im\n" + logo);
        System.out.println("What can I do for you hmm?");
        this.showLine();
    }

    /**
     * Displays a long line for visual effects.
     */
    public void showLine() {
        String line = "-------------------------------";
        System.out.println(line);
    }

    /**
     * Let the user know when the input they made is invalid.
     */
    public void showLoadingError() {
        System.out.println("OHOH the input cannot make it!");
    }

    /**
     * Returns the input the user typed in.
     * @return The input the user typed in.
     * @throws IOException When the user type in invalid input.
     */
    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder sb = new StringBuilder();
        String inp;
        inp = br.readLine();
        return inp;
    }

}
