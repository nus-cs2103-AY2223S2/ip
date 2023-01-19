import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String INDENT_LINE = "____________________________________________________________";

        System.out.println(INDENT_LINE);
        System.out.println("Hello! I'm Vincent");
        System.out.println("What can I do for you?");
        System.out.println(INDENT_LINE);

        while (true) {
            String command = sc.next();
            if (!command.equals("bye")) {
                System.out.println(INDENT_LINE);
                System.out.println(command);
                System.out.println(INDENT_LINE);
            } else {
                System.out.println(INDENT_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(INDENT_LINE);
                break;
            }
        }
    }
}
