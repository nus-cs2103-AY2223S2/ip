import java.util.Scanner;
import java.lang.String;

/**
 * The Duke class implements a personal assistant chatbot
 * that helps a person keep track of various things.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String INDENT = "    ";
    private static final String HORIZONTAL_LINE = "______________________________\n";
    private static final String LOGO
            = INDENT + " ____        _        \n"
            + INDENT + "|  _ \\ _   _| | _____ \n"
            + INDENT + "| | | | | | | |/ / _ \\\n"
            + INDENT + "| |_| | |_| |   <  __/\n"
            + INDENT + "|____/ \\__,_|_|\\_\\___|\n";

    private void greet() {
        System.out.println(INDENT + HORIZONTAL_LINE
                + INDENT + "Hello I'm\n"
                + LOGO
                + INDENT + "What can I do for you?\n"
                + INDENT + HORIZONTAL_LINE);
    }

    private void exit() {
        System.out.println(INDENT + HORIZONTAL_LINE
                + INDENT + "Bye. Hope to see you again soon!\n"
                + INDENT + HORIZONTAL_LINE);
    }

    private void echo(String cmd) {
        System.out.println(INDENT + HORIZONTAL_LINE
                + INDENT + cmd + "\n"
                + INDENT + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner input = new Scanner(System.in);
        String cmd = input.nextLine();
        while (!cmd.equals("bye")) {
            duke.echo(cmd);
            cmd = input.nextLine();
        }
        duke.exit();
        input.close();
    }
}
