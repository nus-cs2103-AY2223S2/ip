package duke;

import java.io.PrintStream;
import java.util.Scanner;

import duke.utils.FormatHelper;

/**
 * Class for methods relating to user interface input and output.
 */
public class Ui {
    private static final String linebreak =
            "    ___________________________________________________________________________________";
    private Scanner input;
    private PrintStream output;

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.input = new Scanner(System.in);
        this.output = new PrintStream(System.out);
    };

    /**
     * Outputs to console text with padded line break before and after.
     * @param s String to output.
     */
    public void reply(String s) {
        if (s.equals("")) {
            return;
        }
        output.println(linebreak);
        output.println(FormatHelper.indent(4, s));
        output.println(linebreak);
    }

    public void greet() {
        reply("Hello! I'm Duke\n What can I do for you?");
    }

    public void signOff() {
        reply("Bye. Hope to see you again!");
    }

    public String readCommand() {
        return input.nextLine();
    }
}
