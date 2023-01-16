import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> listOfThings = new ArrayList<>();

    public static void printWithLines(String text) {
        System.out.println("    ____________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________");

    }

    public static void addItem(String text) {
        listOfThings.add(text);
        printWithLines("added: " + text);
    }

    public static void printList() {
        String totalString = "";
        for (int i = 0; i < listOfThings.size(); i++) {
            totalString += i + ". " + listOfThings.get(i) + "\n" + "    ";
        }
        printWithLines(totalString);
    }
    public static void main(String[] args) {
        printWithLines("Hello! I'm Duke!\n    What can I do for you today?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            if (line.equalsIgnoreCase("bye")) break;
            if (line.equalsIgnoreCase(("list"))) {
                printList();
                continue;
            }
            addItem(line);
        }
        printWithLines("Bye! Hope to see you again soon!");
    }
}

