import java.util.Scanner;
public class Duke {
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Hello! I'm Duke" +
                    "\n" + "What can I do for you?" + "\n" + HORIZONTAL_LINE;
    public static final String BYE_MESSAGE =
            HORIZONTAL_LINE + "\n" + "Bye. Hope to see you again soon!" +
                    "\n" + HORIZONTAL_LINE;


    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver) {
            String userCommands = scanner.nextLine();
            if (userCommands.equalsIgnoreCase("bye")) {
                isOver = true;
                System.out.println(BYE_MESSAGE);
            } else {
                System.out.println(HORIZONTAL_LINE + "\n" + userCommands + "\n" + HORIZONTAL_LINE);
            }
        }

    }
}
