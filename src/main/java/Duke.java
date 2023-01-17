import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    // array that contains all the tasks thus far
    private static ArrayList<Task> listOfThings = new ArrayList<>();

    // printing the texts with lines on top and at the bottom
    public static void printWithLines(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");

    }

    // adding item to the list of things, as well as printing the task that is added
    public static void addItem(String text) {

        Task addedItem = null;
        if (text.startsWith("todo")) {
            String contents = text.replace("todo", "");
            addedItem = new Todo(contents);

        } else if (text.startsWith("deadline")) {
            String contents = text.replace("deadline", "");
            String[] arr = contents.split("/by");
            addedItem = new Deadline(arr[0], arr[1]);
        } else {
            String contents = text.replace("event", "");
            String[] arr1 = contents.split("/from");
            String[] arr2 = arr1[1].split("/to");
            addedItem = new Event(arr1[0], arr2[0], arr2[1]);
        }
        listOfThings.add(addedItem);
        String plural = "";
        if (listOfThings.size() > 1) {
            plural = "s";
        }
        String str = "  " + addedItem.toString() + "\n     Now you have " + listOfThings.size() + " task" + plural + " in the list.";
        str = " Got it. I've added this task:\n     " + str;
        printWithLines(str);
    }

    // printing though the list
    public static void printList() {

        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < listOfThings.size(); i++) {
            totalString += "\n     " + (i+1) + "." + listOfThings.get(i).toString();
        }
        printWithLines(totalString);
    }


    // main driver function
    public static void main(String[] args) {
        printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");
        Scanner input = new Scanner(System.in);
        while (true) {
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

