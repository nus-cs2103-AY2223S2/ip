import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Hello! I'm Duke\n");
            System.out.print("What can I do for you?\n");

            String str= "";

            while (!Objects.equals(str, "bye".toLowerCase(Locale.ROOT))) {
                str = sc.nextLine().toLowerCase();
                System.out.print(str+'\n');
            }

    }


}
