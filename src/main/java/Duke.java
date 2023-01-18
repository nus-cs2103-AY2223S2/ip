import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<String>();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("(\\ (\\ \n" +
                    "(„• ֊ •„) ♡\n" +
                    "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━");

            if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%s. %s", i + 1, tasks.get(i)));
                }
            } else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye bye :( Hope to see you again soon!");
        sc.close();

    }
}
