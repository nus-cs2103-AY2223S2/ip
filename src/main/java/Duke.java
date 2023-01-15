import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================\n";
        System.out.print(welcomeMessage);

        ArrayList<String> list = new ArrayList<>();
        String userPrompt = ">> ";
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(userPrompt);
            String userInput = sc.nextLine();

            if (userInput.equals("list")) {
                if (list.size() == 0) {
                    System.out.println("The list is empty.");
                    continue;
                }

                for (int i = 0; i < list.size(); i++) {
                    String element = list.get(i);
                    String indexString = Integer.toString(i + 1);
                    System.out.println(indexString + ". " + element);
                }
            } else if (userInput.equals("bye")) {
                System.out.println("Exiting...");
                break;
            } else {
                list.add(userInput);
                System.out.println("Added: " + userInput);
            }
        }
    }
}
