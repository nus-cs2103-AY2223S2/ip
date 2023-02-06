import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

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

        List<String> commandList = new ArrayList<>();

        this.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                this.printCommandList(commandList);
            } else if (!command.equals("bye")) {
                this.echoCommand(command);
                commandList.add(command);
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
                "\n\t" + "added: " + command +
                "\n\t____________________________________________________________");
    }

    public void printCommandList(List<String> commandList) {
        System.out.println("\t____________________________________________________________");

        for (int i = 0; i < commandList.size(); i++) {
            int numbering = i + 1;
            System.out.println("\t" + numbering + ". " + commandList.get(i));
        }

        System.out.println("\t____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("\t____________________________________________________________" +
                "\n\tBye. Hope to see you again soon!" +
                "\n\t____________________________________________________________");
    }

}
