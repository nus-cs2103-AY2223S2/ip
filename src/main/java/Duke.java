import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("----------------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while (!str.equals("bye")) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println(str);
            System.out.println("----------------------------------------------------------------------");
            input = new Scanner(System.in);
            str = input.nextLine();
        }
        System.out.println("----------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------------------------------------------");
    }
}
