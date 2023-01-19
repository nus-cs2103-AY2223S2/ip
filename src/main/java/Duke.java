import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println("------------------------------------------------------------------------------");
        String cur = sc.nextLine();
        while(!cur.equals("bye")) {
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(cur);
            System.out.println("------------------------------------------------------------------------------");
            cur = sc.nextLine();
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------------------------------------------");
    }
}
