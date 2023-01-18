import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.runApp();
    }

    public void greetingMessage() {
        printMessage("Hello! I'm Duke\n\tWhat can I do for you?");
    }

    public void goodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void printMessage(String s) {
        printLongLine();
        System.out.println("\t" + s);
        printLongLine();
    }

    public void echoMessage(String s) {
        printMessage(s);
    }

    public void printLongLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void runApp() {
        greetingMessage();
        boolean enteredBye = false;
        while (!enteredBye) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                enteredBye = true;
            } else {
                echoMessage(input);
            }
        }
        goodbyeMessage();
    }
}





