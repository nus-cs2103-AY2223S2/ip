import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String EXIT_COMMAND = "bye";
        Scanner user_input = new Scanner(System.in);

        System.out.println("Hello! I'm Anton's Bot");
        System.out.println("What can I do for you?");

        while (true){
            String current_input = user_input.next();
            if (current_input.equals(EXIT_COMMAND)) {
                System.out.println("        Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println("        " + current_input);
            }
        }
        user_input.close();
    }
}
