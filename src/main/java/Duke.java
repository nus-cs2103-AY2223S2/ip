import java.util.Scanner;
public class Duke {
    public static String formatOutput(String out) {
        final String divider = "____________________________________________________________";
        return String.format("\t%s\n\t%s\n\t%s", divider, out, divider);
    }
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println(formatOutput("Hello! I'm Duke\n\tWhat can I do for you?"));
        String inputStr = inputScanner.nextLine().trim();
        while (!inputStr.equals("bye")) {
            System.out.println(formatOutput(inputStr));
            inputStr = inputScanner.nextLine().trim();
        }
        System.out.println(formatOutput("Bye. Hope to see you again soon!"));
    }
}
