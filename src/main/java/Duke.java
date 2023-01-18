import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i));
                }

                System.out.println(line);
            } else {
                list.add(input);
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }

            input = scanner.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
