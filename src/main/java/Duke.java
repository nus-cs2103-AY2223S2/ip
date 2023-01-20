import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm tyy\nWhat can I do for you?");

        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        while (!input.equals("bye")) {
            System.out.println(" " + input);
            input = scan.next();
        }
        System.out.println(" " + "Ciao. Hope to see you again soon!");
    }
}
