import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";

    private static final String INDENTATION = " ".repeat(5);

    private static final List<String> addedTexts = new ArrayList<>();

    private static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    private static void addText(String msg) {
        addedTexts.add(msg);
        String displayedMsg = "added: " + msg;
        echo(displayedMsg);
    }

    private static void listTexts() {
        String displayedMsg = IntStream.range(0, addedTexts.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, addedTexts.get(i)))
                .collect(Collectors.joining("\n"));
        echo(displayedMsg);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            echo("Hello! I'm Duke\nWhat can I do for you?");
            do {
                String in = sc.nextLine();
                if (in.equals("bye")) {
                    echo("Bye. Hope to see you again soon!");
                    break;
                } else if (in.equals("list")) {
                    listTexts();
                } else {
                    addText(in);
                }
            } while (true);
        }
    }
}
