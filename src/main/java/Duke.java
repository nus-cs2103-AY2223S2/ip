import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from! I'm Duke\n" + "What can I do for you?\n");
        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.print("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }

    }
}
