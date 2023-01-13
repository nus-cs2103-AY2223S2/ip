import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Duke {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";

    private static final String INDENTATION = " ".repeat(5);

    private static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            echo("Hello! I'm Duke\nWhat can I do for you?");
            do {
                String in = sc.nextLine();
                if (in.equals("bye")) {
                    echo("Bye. Hope to see you again soon!");
                    break;
                }
                echo(in);
            } while (true);
        }
    }
}
