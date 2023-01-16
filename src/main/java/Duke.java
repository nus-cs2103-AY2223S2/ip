import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        while(!s.equals("bye")) {
            System.out.println(s);
            s = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
