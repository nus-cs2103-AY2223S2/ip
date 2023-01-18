import java.util.Scanner;

public class Willy {
    public static void main(String[] args) {
        String logo = "Willy";
        System.out.println("Hello! I'm " + logo +
                "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();

            if (command.equals("list")) {
                System.out.println("list\n");
            }
            if (command.equals("blah")) {
                System.out.println("blah\n");
            }
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                sc.close();
            }
        }

    }
}
