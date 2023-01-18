import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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
            dukeSpeak(userInput);
        }

        dukeSpeak("Bye. Hope to see you again soon!");

    }

    public static void dukeSpeak(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
