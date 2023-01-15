import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final static String lines = "\t____________________________________________________________\n";
    final static String greet = lines +
            "\tHello! I'm Duke\n" +
            "\tWhat can I do for you?\n" +
            lines;
    static List<String> textList;

    public static void printList() {
        System.out.print(lines);
        for (int i = 0; i < textList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + textList.get(i));
        }
        System.out.print(lines);
    }

    public static void addList(String text) {
        textList.add(text);
        System.out.println(lines + "\tadded: " + text +"\n" + lines);
    }

    public static void main(String[] args) {
        textList = new ArrayList<>();
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);

        String commands = sc.nextLine();
        while (!commands.equals("bye")) {
            if (commands.equals("list")) {
                printList();
            } else {
                addList(commands);
            }
            commands = sc.nextLine();
        }
        System.out.println(
                lines +
                        "\tBye. Hope to see you again soon!\n" +
                        lines
        );
    }
}
