import java.util.Scanner;

public class Duke {
    public static void printReply(String message){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + message);
        System.out.println("\t____________________________________________________________");
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String message = "Hello! I'm C-3PO, Human Cyborg Relations.\n\tWhat can I do for you?";
        printReply(message);
        while(true) {
            String command = input.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                message = "Bye. Hope to see you again soon!";
                printReply(message);
                break;
            }
            printReply(command);
        }
        input.close();
    }
}
