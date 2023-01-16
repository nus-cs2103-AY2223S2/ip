import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greetings();
        acceptCommands();
        exit();
    }

    private static void greetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + ", your personal assistant.\n What can I do for you today?");
    }

    private static void acceptCommands() {
        Scanner sc = new Scanner(System.in);
        String command = "";
        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(command);
        }
        sc.close();
    }

    private static void exit() {
        System.out.println("I hope you've managed to be productive today. Bye!");
        System.exit(0);
    }
}
