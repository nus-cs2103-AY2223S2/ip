import java.util.Scanner;

public class Duke {
    public static void print(String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Duke\n" +
                "     What can I do for you?";
        print(greeting);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                System.exit(0);
            }
            print(input);
        }
    }
}