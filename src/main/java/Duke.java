import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> actions = new ArrayList<String>();

    public static void main(String[] args) {
        System.out.println("Hello from! I'm a Cookie Monster\n" + "What can I do for you?\n");
        Scanner reader = new Scanner(System.in);

        while (true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                System.out.print("Bye I'm gonna go eat cookies. Hope to see you again soon!");
                break;
            }
            else if (input.equals("list")) {
                int len = actions.size();
                for (int i = 0; i < len; i++) {
                    System.out.println(i + 1 + ". " + actions.get(i));
                }

            } else {
                actions.add(input);
                System.out.println("NOM NOM NOM added: " + input);
            }

        }

    }
}
