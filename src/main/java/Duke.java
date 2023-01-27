import java.util.Scanner;

/**
 * The Duke class implements a personal assistant chatbot that helps the user to keep track of various tasks.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String INDENTATION = "    ";
    private static final String LINE = INDENTATION + "____________________________________________________________";
    private static final String LOGO
            = INDENTATION + " ____        _        \n"
            + INDENTATION + "|  _ \\ _   _| | _____ \n"
            + INDENTATION + "| | | | | | | |/ / _ \\\n"
            + INDENTATION + "| |_| | |_| |   <  __/\n"
            + INDENTATION + "|____/ \\__,_|_|\\_\\___|\n";

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

    private void greet() {
        System.out.println(INDENTATION + LINE
                + INDENTATION + "Hello I'm\n"
                + LOGO
                + INDENTATION + "What can I do for you?\n"
                + INDENTATION + LINE);
    }

    private void exit() {
        System.out.println(INDENTATION + LINE
                + INDENTATION + "Bye. Hope to see you again soon!\n"
                + INDENTATION + LINE);
    }

    private void echo(String cmd) {
        System.out.println(INDENTATION + LINE
                + INDENTATION + cmd + "\n"
                + INDENTATION + LINE);
    }
}
