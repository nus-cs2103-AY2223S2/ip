import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static ArrayList<String> stringStorage = new ArrayList<String>();

    public static void main(String[] args) {
        greetings();

        while (true) {
            String input = getUserInput();
            if (input.equals("bye"))
                break;

            if (input.contains("list")) {
                listItem();
                continue;
            }

            storeItem(input);
        }

        bye();
    }

    public static void greetings() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static String getUserInput() {
        String userInput = in.nextLine();
        return userInput;
    }

    public static void storeItem(String item) {
        stringStorage.add(item);
        System.out.print("added: ");
        echo(item);
    }

    public static void listItem() {
        int size = stringStorage.size();

        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + stringStorage.get(i));
    }
}
