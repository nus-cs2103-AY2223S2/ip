import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, Duke here. How can I help you?");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userLine = sc.nextLine();
            if (userLine.equals("bye")) {
                System.out.println("Bye, hope to see you again.");
                break;
            }
            System.out.println(userLine);
        }
    }
}
