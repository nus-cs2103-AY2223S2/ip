import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chiwa, your personal chatbot (◔◡◔✿)");
        System.out.println("What can I do for you today?");

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> lst = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye~ Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                String reply = "";
                for (int i = 0; i < lst.size(); i++) {
                    reply += String.format("%d. " + lst.get(i) + "\n", i + 1);
                }
                System.out.println(reply);
            } else {
                lst.add(input);
                System.out.println("added: " + input + "\n");
            }
        }
        scanner.close();
    }
}
