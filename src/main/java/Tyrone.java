import java.util.Scanner;

public class Tyrone {
    
    public static void echo() {
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

    public static void main(String[] args) {

        System.out.println("    > What's up dawg");
        Tyrone.echo();
    }
}
