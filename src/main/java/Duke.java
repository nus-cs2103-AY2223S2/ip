import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chiwa, your personal chatbot (◔◡◔✿)");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
        scanner.close();
    }
}
