import java.util.Scanner;
import java.util.ArrayList;
public class James {
    public static void main(String[] args) {
        ArrayList<String> inputs = new ArrayList<>();
        System.out.println("Hello! I'm James.");
        System.out.println("How can I help you today?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
                break;
            }
            if (input.equals("list")) {
                System.out.println("List of inputs from user:");
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println((i + 1) + ". " + inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }

}
