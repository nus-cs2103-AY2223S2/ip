import java.util.Scanner;

/*
Level-1
idea:
- design a "reply" method to format the duke reply text
- print hello statement
- scanf input
    - print indented horizontal line
    - print input
    - print indented horizontal line

 */
public class Duke {
    private static void reply(String s) {
        System.out.println("\t"
                + "____________________________________________________________");
        System.out.println("\t" + s.replace("\n", "\n    "));
        System.out.println("\t"
                + "____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        reply("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                reply("Bye! Hope to see you again soon!");
                break;
            }
            reply(userInput);
        }
    }
}
