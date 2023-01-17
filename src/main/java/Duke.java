// Tan Matthew Simon Castaneda
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //initialize scanner
        Scanner sc = new Scanner(System.in);
        //intro message from duke

        System.out.println("Whats good its duke\nwhat do you want from me");

        //to store all the strings

        String[] storedMessages = new String[100];

        //while not bye
        //might want to do switch case?
        int listCounter = 0;
        while(true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < storedMessages.length; i++) {
                    if (storedMessages[i] == null) {
                        break;
                    }
                    System.out.printf("%d." + storedMessages[i] + "\n", i+1);
                }
            } else {
                storedMessages[listCounter] = command;
                listCounter++;
                System.out.println(command);
            }
        }

        //exit message from duke
        System.out.println("Thanks for using me brother");
    }
}
