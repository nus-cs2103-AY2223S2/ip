import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String dialogSeparator = "____________________________________________________________";

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String dialogSeparator = "____________________________________________________________";
        String greeting = logo + "\n" + dialogSeparator + "\nHello! I'm Duke\nWhat can I do for you?\n" + dialogSeparator;
        System.out.println(greeting);

    }

    public static void main(String[] args) {
        greeting();
        String input;
        ArrayList<String> tasks = new ArrayList<>();

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(dialogSeparator + "\n" + "Bye. Hope to see you again soon!" + "\n" + dialogSeparator);
                break;
            } else if (input.equals("list")) {
                int i = 1;
                System.out.println(dialogSeparator);
                for (String item : tasks) {
                    System.out.println(i + ". " + item);
                }
                System.out.println(dialogSeparator);

            } else {
                tasks.add(input);
                System.out.println(dialogSeparator + "\n" + "added: " + input + "\n" + dialogSeparator);
            }
        }
    }
}
