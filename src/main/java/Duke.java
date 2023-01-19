import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Duke {
    public static String newline = "\t____________________________________________________________";
    public static void main(String[] args) {
        String logo = "  ╔╗        \n" +
                    "  ║║        \n" +
                    "  ║║╔══╗╔══╗\n" +
                    "╔╗║║║╔╗║║╔╗║\n" +
                    "║╚╝║║╚╝║║║═╣\n" +
                    "╚══╝╚══╝╚══╝\n" +
                    "            \n" +
                    "            \n";

        String greeting = "\tHello! I'm Joe\n" +
                "\tWhat Can I do for you?";

        System.out.println("Hello from\n" + logo);
        System.out.println(newline);
        System.out.println(greeting);
        System.out.println(newline);
        Scanner sc = new Scanner(System.in);
        while(true) {
            getResponse(sc);
        }
    }

    public static void returnList() {

    }

    public static void sayBye() {

    }

    public static void reply() {
    }

    public static void getResponse(Scanner sc){
        String input = sc.nextLine();
        System.out.println(newline);
        switch (input) {
            case ("bye"):
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(newline);
                System.exit(0);
                break;
            default:
                System.out.println("\t" + input);
        }

    }
}
