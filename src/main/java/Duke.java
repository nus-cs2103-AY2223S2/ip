import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);
    static ArrayList<Task> stringStorage = new ArrayList<Task>();

    public static void main(String[] args) {
        greetings();

        while (true) {
            String input = getUserInput();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            if (command.equals("bye"))
                break;

            if (command.contains("list")) {
                listItem();
                continue;
            }

            if (command.contains("unmark")) {
                unmarkItem(inputSplit[1]);
                continue;
            }

            if (command.contains("mark")) {
                markItem(inputSplit[1]);
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
        stringStorage.add(new Task(item));

        System.out.print("added: ");
        echo(item);
    }

    public static void listItem() {
        int size = stringStorage.size();
        
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < size; i++)
            System.out.println((i + 1) + ". " + stringStorage.get(i));
    }

    public static void markItem(String item) {
        int index = Integer.parseInt(item) - 1;
        Task task = stringStorage.get(index);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public static void unmarkItem(String item) {
        int index = Integer.parseInt(item) - 1;
        Task task = stringStorage.get(index);
        task.markAsNotDone();

        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }
}
