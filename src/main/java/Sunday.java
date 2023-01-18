import java.util.Scanner;
public class Sunday {
    private static State state = State.GREET;
    public static void main(String[] args) {
        System.out.println("Hi! I'm Sunday, pleasure to meet you!");
        System.out.println("What can I do for you?");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        while (state != State.EXIT) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                state = State.EXIT;
            } else {
                state = State.ECHO;
                System.out.println(input);
                System.out.println();
            }
        }
        System.out.println("Goodbye and have a pleasant day!");
    }
}
