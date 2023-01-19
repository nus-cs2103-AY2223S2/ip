import java.util.Scanner;

public class Duke {
    private static final String FULL_LINE = "_______________________________________________\n";
    public static void main(String[] args) {
        String welcomeString = "Hello I'm Duke! Type anything and I'll echo it.\n";
        String byeString = "Bye. Hope to see you again soon!\n";
        System.out.println(FULL_LINE + welcomeString + FULL_LINE + "\n");

        String input = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(FULL_LINE + byeString + FULL_LINE);
                break;
            } else {
                System.out.println(FULL_LINE + input + "\n" + FULL_LINE);
            }
        }
    }
}
