import java.util.Scanner;

public class Duke {
    public static void printCommand(String command) {
        String line = "__________________________________\n";
        System.out.println(line);
        System.out.println(command);
        System.out.println(line);
    }
    public static void main(String[] args) {
        String greeting = "Yo i'm SmartDuke.\n" +
                "     how can i help you?";

        Duke.printCommand(greeting);

        Scanner userInput = new Scanner(System.in);

        while (true) {
            String command = userInput.nextLine();
            if (command.equals("bye")) {
                Duke.printCommand("ok bye");
                break;
            }
            Duke.printCommand(command);
        }

//
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
    }
}
