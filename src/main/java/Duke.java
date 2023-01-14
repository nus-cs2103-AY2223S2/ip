import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void printRowLine() {
        println("_________________________________________");
    }

    public static void println(String s) {
        System.out.println("\t" + s);
    }

    public static void printAddedInput(String s) {
        printRowLine();
        println(String.format("added: %s", s));
        printRowLine();
    }

    public static void printArray(List<String> items) {
        printRowLine();
        for (int i = 0; i < items.size(); i++) {
            println(String.format("%d. %s", i + 1, items.get(i)));
        }
        printRowLine();
    }

    public static void greetUser() {
        printRowLine();
        println("\tHi There! I'm Shao");
        println("\tWhat can I do for you?");
        printRowLine();
    }

    public static void exitUser() {
        printRowLine();
        println("Bye! Have a nice day!");
        printRowLine();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<String> data = new ArrayList<>();

        greetUser();

        // Prompting
        while (true) {
            String input = scan.next().toLowerCase();
            switch (input) {
                case "bye":
                    exitUser();
                    scan.close();
                    return;
                case "list":
                    printArray(data);
                    break;
                default:
                    data.add(input);
                    printAddedInput(input);
                    break;

            }
        }
    }
}
