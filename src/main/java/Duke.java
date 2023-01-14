import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        while(scanner.hasNext()) {
            String inputString = scanner.nextLine();

            if(inputString.equals("bye")) {
                System.out.println("Duke: " + inputString + ". Hope I never see you again!");
            }
            else {
                System.out.println("Duke: " + inputString);
            }
        }
        scanner.close();
    }
}
