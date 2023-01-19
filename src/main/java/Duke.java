import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String INDENT_LINE = "____________________________________________________________";

        System.out.println(INDENT_LINE);
        System.out.println("Hello! I'm Vincent");
        System.out.println("What can I do for you?");
        System.out.println(INDENT_LINE);

        ArrayList<String> commandList = new ArrayList<>();

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(INDENT_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(INDENT_LINE);
                break;
            } else if (command.equals("list")) {
                System.out.println(INDENT_LINE);
                for (int i = 0; i < commandList.size(); i++) {
                    System.out.println((i+1) + ". " + commandList.get(i));
                }
                System.out.println(INDENT_LINE);
            } else {
                commandList.add(command);
                System.out.println(INDENT_LINE);
                System.out.println("added: " + command);
                System.out.println(INDENT_LINE);
            }
        }
    }
}
