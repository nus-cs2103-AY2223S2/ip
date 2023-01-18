import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Print intro
        String intro = "Hello! I'm Duke\n What can I do for you?";
        System.out.println(intro);

        // Create Scanner object
        Scanner sc = new Scanner(System.in);

        // Always ready to receive input
        while (true) {
            String input = sc.nextLine();
            if (input != null && input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(input);
        }
    }
}
