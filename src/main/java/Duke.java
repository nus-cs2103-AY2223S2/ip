import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = scanner.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
