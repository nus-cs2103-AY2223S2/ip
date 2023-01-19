import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<>();

        System.out.println("Hello, I am Duke. \nWhat can I do for you?");

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.println(i+1 + ". " + inputList.get(i));
                }
            } else {
                System.out.println("added: " + input);
                inputList.add(input);
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();
    }
}
