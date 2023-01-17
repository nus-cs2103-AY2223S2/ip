import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String greeting = "Hello! I'm Duke! \n What can I do for you? \n";
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        while (!userInput.equals("bye")) {
            System.out.println("\n" + userInput + "\n");
            userInput = scanner.next();
        }
        System.out.println("\nBye. Hope to see you again soon!\n");
    }
}
