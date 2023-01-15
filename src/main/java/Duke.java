import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String welcomeMessage = "============================================================\n"
                + "Welcome to Duchess\n"
                + "============================================================\n";
        String userPrompt = ">> ";
        System.out.print(welcomeMessage + userPrompt);

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            System.out.print(userPrompt);
            userInput = sc.nextLine();
        }

        // user input is "bye"
        System.out.println("Exiting...");
    }
}
