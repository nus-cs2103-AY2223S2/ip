import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\nL"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    " + "Hello I'm Monkey D Luffy\n    What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("    " + input);
            input = sc.nextLine();
        }
        System.out.println("    " + "Bye. Hope to see you again soon!");
    }
}
