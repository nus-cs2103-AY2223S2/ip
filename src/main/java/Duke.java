import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! Im Zhizhou's Chatbot");
        System.out.println("What can I do for you?");
        String output = "";
        while (!(output = userInput.next()).equals("bye")) {

            System.out.println("   " + output);
        }
        System.out.println("   Bye. Hope to see you again soon!");
    }
}
