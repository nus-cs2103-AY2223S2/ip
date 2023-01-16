import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Saturday saturday = new Saturday();
        String userInput = scanner.nextLine();

        // Saturday Active
        while (!userInput.equals("bye")) {
            saturday.input(userInput);
            userInput = scanner.nextLine();
        }

        saturday.exit();

    }
}
