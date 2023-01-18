import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        String userInput = null;
        while(!(userInput = scan.nextLine()).equals("bye")) {
            if (userInput.equals("list")) {
                String message = "";
                for (int i = 0; i < list.size(); i++) {
                    message += i != 0 ? "\n" : "";
                    message += (i+1) + ". " + list.get(i);
                }
                dukeSpeak(message);
            } else {
                list.add(userInput);
                dukeSpeak("added: " + userInput);
            }
        }

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
