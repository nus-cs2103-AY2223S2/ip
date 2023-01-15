import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String dialogSeparator = "____________________________________________________________";
        String greeting = logo+"\n"+dialogSeparator + "\nHello! I'm Duke\nWhat can I do for you?\n" + dialogSeparator;

        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(dialogSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + dialogSeparator);
                break;
            } else {
                System.out.println(dialogSeparator + "\n" + input + "\n" + dialogSeparator);
            }
        }
    }
}
