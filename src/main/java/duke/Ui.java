package duke;

import duke.utils.FormatHelper;

import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String linebreak = "    ___________________________________________________________________________________";
    private Scanner input;
    private PrintStream output;

    public Ui() {
        this.input = new Scanner(System.in);
        this.output = new PrintStream(System.out);
    };

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
}
