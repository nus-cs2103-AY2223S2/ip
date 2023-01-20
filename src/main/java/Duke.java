import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Hello from Bench Monster");
        System.out.println("What can I do for you?");
        String type = s.nextLine();

        while (true) {
            if (type.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(type);
                type = s.nextLine();
            }
        }
    }
}//class
