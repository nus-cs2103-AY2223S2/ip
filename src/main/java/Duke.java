import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    private static ArrayList<Task> listOfThings = new ArrayList<>();

    public static void printWithLines(String text) {
        System.out.println("    ____________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________");

    }

    public static void addItem(String text) {
        Task currTask = new Task(text);
        printWithLines("added: " + text);
        listOfThings.add(currTask);

    }

    public static void printList() {
        String totalString = "";
        for (int i = 0; i < listOfThings.size(); i++) {
            totalString += (i+1) + ". " + listOfThings.get(i).toString() + "\n" + "    ";
        }
        printWithLines(totalString);
    }


    public static void main(String[] args) {
        printWithLines("Hello! I'm Duke!\n    What can I do for you today?");
        while (true) {
            Scanner input = new Scanner(System.in);
            String line = input.nextLine();
            String lowerLine = line.toLowerCase();
            if (lowerLine.equals("bye")) {
                break;
            } else if (lowerLine.equals("list")) {
                printList();
                continue;
            } else if (lowerLine.startsWith("mark") || lowerLine.startsWith("unmark")) {
               String[] arr = lowerLine.split(" ");
               int idx = Integer.parseInt(arr[1]) - 1;
               String command = arr[0];
               if (command.equals("mark")) {
                   listOfThings.get(idx).markDone();
               } else {
                   listOfThings.get(idx).markUndone();
               }
            } else {
            addItem(line);
            }

        }
        printWithLines("Bye! Hope to see you again soon!");
    }
}

