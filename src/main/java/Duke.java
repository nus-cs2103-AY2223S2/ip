import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");

        while (true) {
            String a = sc.nextLine().toLowerCase();
            if (Objects.equals(a, "bye".toLowerCase())) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println(a);
            }

        }
    }
}
