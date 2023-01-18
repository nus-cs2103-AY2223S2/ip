
import java.util.Scanner;
public class Duke {
    /*public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }*/

    public static void main(String[] args) {
        String greetings = "Heyyo, Pandora at your service \n"
                + "What can I do for you?";
        System.out.println(greetings);

        //Initial inputs
        Scanner userInput = new Scanner(System.in);
        String userMessage = userInput.nextLine();

        //To-do-list

        while (true) {
            if (userMessage.equals("bye")) {
                System.out.print("  " + "Cya~ Till next time!");
                break;
            } else {
                System.out.println("  " + userMessage);
                Scanner newInput = new Scanner(System.in);
                userMessage = newInput.nextLine();
            }
        }

    }
}
