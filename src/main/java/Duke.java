import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke");
        System.out.println("What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String message = scanner.nextLine();
            if(!message.equals("bye")) {
                System.out.println(message);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
