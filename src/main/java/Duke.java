import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "___________________________________________________\n";

    public void greet() {
        System.out.println(Duke.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Duke.LINE);
    }

    public void exit() {
        System.out.println(Duke.LINE
                + "Bye. Hope to see you again soon!\n"
                + Duke.LINE);
    }

    public void start(Scanner sc) {
        this.greet();

        while(sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                this.exit();
                break;
            } else {
                System.out.println(Duke.LINE + input);
                System.out.println(Duke.LINE);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        duke.start(scanner);
    }
}
