import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        while (true) {
            Scanner bucky = new Scanner(System. in);
            String str = bucky.nextLine();
            if (str.equals("bye")) {
                System.out.println ("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(str);
            }
        }
        
    }
}
