import java.sql.SQLOutput;
import java.util.*;

class Crystal {
    public static void main(String[] args) {
        System.out.println(" ____________________________________________________________");
        System.out.println(" Hi! I am CRYSTAL.\n How may I be of assistance?");
        System.out.println(" ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        while (!input.equals("bye")) {
            System.out.println(" ____________________________________________________________");
            System.out.println(input);
            System.out.println(" ____________________________________________________________");
            input = sc.next();
        }
        System.out.println(" ____________________________________________________________");
        System.out.println(" Thank You. Please come by again~!");
        System.out.println(" ____________________________________________________________");

    }
}