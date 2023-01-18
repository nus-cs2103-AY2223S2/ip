import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("(\\ (\\ \n" +
                    "(„• ֊ •„) ♡\n" +
                    "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye bye :( Hope to see you again soon!");
        sc.close();

    }
}
