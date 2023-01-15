import java.util.Scanner;

public class YJ {
    public static void main(String[] args) {

        // Greet the user, simply echo commands entered by the user, and exit when the user types bye.
        System.out.println("Hello! I'm YJ. What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("YJ says: " + input);
            input = sc.nextLine();
        }

        sc.close();
    }
}
