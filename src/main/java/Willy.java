import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Willy {
    public static void main(String[] args) {
        String logo = "Willy";
        System.out.println("Hello! I'm " + logo +
                "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);

        List<String> lst = new ArrayList<String>();

        while (true) {
            String command = sc.nextLine();

            if (command.equals("blah")) {
                System.out.println("blah\n");
            }

            if (command.equals("read book")) {
                System.out.println("added: read book\n");
                lst.add("read book");
            }
            if (command.equals("return book")) {
                System.out.println("added: return book\n");
                lst.add("return book");
            }

            if (command.equals("list")) {
                // System.out.println("list\n");
                for (int index = 0; index < lst.size(); index++) {
                    System.out.println((index + 1) + " " + lst.get(index));
                }
            }

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
                sc.close();
            }
        }

    }
}
