import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> lst = new ArrayList<>();
        System.out.println("---------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("---------------------------------------------------------------------");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        while (!str.equals("bye")) {
            System.out.println("--------------------------------------------------------------------");
            if (str.equals("list")) {
                int counter = 1;
                for (String temp : lst) {
                    System.out.println(counter++ + ". " + temp);
                }
            } else {
                System.out.println("added: " + str);
                lst.add(str);
            }
            System.out.println("--------------------------------------------------------------------");
            input = new Scanner(System.in);
            str = input.nextLine();
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------------------------------");
    }
}
