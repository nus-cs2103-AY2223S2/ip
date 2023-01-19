import java.util.Scanner;
import java.util.Objects;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Somebody\n" + "What can I do for you?");
        Scanner scn = new Scanner(System.in);
        while (true) {

            String input = scn.nextLine();

            if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scn.close();
                return;
            } else {
                System.out.println(input);
            }

        }
    }
}
