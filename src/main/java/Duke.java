import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    public static void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        printHorizontalLine();

        System.out.println(
            "Karen:\n" +
            "Can I speak to your manager?\n" +
            "Just kidding, this is Karen. How can I help you today?"
        );

        printHorizontalLine();

        String command = sc.nextLine();

        while (!command.equals("bye")) {
            printHorizontalLine();
            System.out.println("Karen:");

            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String currTask = tasks.get(i);
                    System.out.printf("%d. %s\n", i + 1, currTask);
                }
            } else {
                tasks.add(command);
                System.out.printf("added: %s\n", command);
            }

            printHorizontalLine();
            command = sc.nextLine();
        }

        printHorizontalLine();
        System.out.println("Karen:\n" + "Bye. This was of great inconvenience.");
        printHorizontalLine();
    }
}
