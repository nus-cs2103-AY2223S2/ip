import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<String> storedStrings = new ArrayList<>();

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
        printNewLine();
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        while(true) {
            getResponse(sc);
        }
    }

    public static void printNewLine() {
        String newline = "\t____________________________________________________________";
        System.out.println(newline);
    }

    public static void returnList() {
        for (int i = 1; i  <= storedStrings.size(); i++) {
            System.out.println(i + ". " + storedStrings.get(i-1));
        }
        printNewLine();
    }

    public static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
        printNewLine();
        System.exit(0);
    }

    public static void reply(String input) {
        storedStrings.add(input);
        System.out.println("added: " + input);
        printNewLine();
    }

    public static void getResponse(Scanner sc){
        String input = sc.nextLine();
        printNewLine();
        switch (input) {
            case ("bye"):
                sayBye();
                break;
            case ("list"):
                returnList();
                break;
            default:
                reply(input);
                break;
        }

    }
}
