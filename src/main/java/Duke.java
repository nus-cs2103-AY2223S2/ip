import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n---------------------------------");
        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println("---------------------------------\n    " + input + "\n---------------------------------");
            input = sc.next();
        }
        System.out.println("---------------------------------\nBye. Hope to see you again soon!");
    }
}
