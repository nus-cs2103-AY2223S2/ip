import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String start_message = "Hello! I'm Duke\n" +  "What can I do for you?";
        System.out.println(start_message);
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        while (!s.equals("bye")) {
            System.out.println(s);
            s = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
