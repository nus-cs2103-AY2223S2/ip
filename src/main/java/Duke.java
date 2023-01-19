import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("    > What's up dawg");

        String echo = "";
        Scanner sc = new Scanner(System.in);

        while (true) {
            echo = sc.nextLine();

            System.out.println("    > " + echo);
            if (echo.equals("bye")) {
                break;
            }
        }
    }
}
