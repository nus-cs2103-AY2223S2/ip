import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String out = greeting();
        print_structured_string(out);

        // read input
        Scanner scanner = new Scanner(System.in);

        String inMsg = null;
        do {
            inMsg = scanner.nextLine();
            String output = echo(inMsg);
            print_structured_string(output);
        } while (!isEnd(inMsg));

        print_structured_string(endMsg());
    }

    public static void print_structured_string(String s) {
        String longLine = "____________________________________________________________";
        System.out.println(longLine + "\n" + s + "\n" + longLine);
    }

    public static String greeting() {
        return "Hello! I'm Duke \nWhat can I do for you?";
    }

    public static String echo(String s) {
        return s;
    }

    public static String endMsg() {
        return "Bye. Hope to see you again soon!";
    }

    public static boolean isEnd(String s) {
        return Objects.equals(s.toLowerCase(), "bye");
    }
}
