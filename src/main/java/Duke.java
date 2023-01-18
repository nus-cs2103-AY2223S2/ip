import java.util.List; // Import List class
import java.util.LinkedList; // Import LinkedList class
import java.util.Scanner;  // Import the Scanner class

public class Duke {

    private static Scanner getInput = new Scanner(System.in); // Create a static Scanner object

    private static List<Task> storedInputs = new LinkedList<>(); // List to store inputs

    public static void main(String[] args) {
        System.out.println(intro());
        String s = askForInput();
        while(!s.equals("bye")) {
            if (s.equals("list")) {
                System.out.println("\nHere are the tasks in your list:\n" + printList()); 
            } else {
                storedInputs.add(new Task(s));
                System.out.println("\nadded: " + s + "\n");
            }
            s = askForInput();
        }
        System.out.println("Good Riddance!");
    }

    private static String logo() {
        return " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    }

    private static String intro() {
        return "Hello! I'm\n" + logo() + "\nWhat can I do for you?";
    }

    private static String askForInput() {
        System.out.print("> ");
        return getInput.nextLine();
    }

    private static String printList() {
        String s = "";
        for (int i = 1; i <= storedInputs.size(); i++) {
            s += i + ". " + storedInputs.get(i-1) + "\n";
        }
        return s;
    }
}
