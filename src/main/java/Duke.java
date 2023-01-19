import java.util.*;
public class Duke {
    private static void printDashes() {
        System.out.println ("****************************************");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);

        printDashes();
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! This is Esther!");
        System.out.println("How can I help you today? ^_^");
        printDashes();

        while(true) {
            String userInput = sc.nextLine();
            if(!userInput.equals("bye")) {
                System.out.println("Your input is: " + userInput);
                printDashes();
            } else {
                System.out.println("Bye. Hope to see you again soon! ^_^");
                printDashes();
                break;
            }
        }
    }
}
