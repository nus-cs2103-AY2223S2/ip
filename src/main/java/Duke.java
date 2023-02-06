import java.util.Scanner;

public class Duke {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.chatDuke();
    }

    public void chatDuke() {

        this.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = sc.nextLine();
            if (!command.equals("bye")) {
                this.echoCommand(command);
            } else {
                saidBye = true;
                this.printByeMessage();
            }
        }
    }

    public void printGreetingMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\tHello! I'm Duke\n" +
                "\tWhat can I do for you?" +
                "\n\t____________________________________________________________");
    }

    public void echoCommand(String command) {
        System.out.println("\t____________________________________________________________" +
                "\n\t" + command +
                "\n\t____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

}
