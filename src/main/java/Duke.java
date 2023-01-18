import java.util.List; // Import List class
import java.util.LinkedList; // Import LinkedList class
import java.util.Scanner;  // Import the Scanner class

public class Duke {

    private static Scanner getInput = new Scanner(System.in); // Create a static Scanner object

    private static List<Task> storedInputs = new LinkedList<>(); // List to store inputs

    public static void main(String[] args) {
        System.out.println(intro());
        
        String userInput = askForInput();
        EventType curEvent = decodeInput(userInput);
        loop: while (true) {
            switch (curEvent) {
            case ADD:
                storedInputs.add(new Task(userInput));
                System.out.println("\nadded: " + userInput + "\n");
                break;
            case BYE:
                break loop;
            case LIST:
                System.out.println("\nHere are the tasks in your list:\n" + printList());
                break;
            case MARK:
                markEvent(userInput);
                break;
            case UNMARK:
                unmarkEvent(userInput);
                break;
            case TODO:
                todoEvent(userInput);
                break;
            case DEADLINE:
                deadlineEvent(userInput);
                break;
            case EVENT:
                eventEvent(userInput);
                break;
            }
            
            userInput = askForInput();
            curEvent = decodeInput(userInput);
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

    private static EventType decodeInput(String input) {
        String[] arr = input.split(" ");
        
        if (arr[0].equals("bye")) {
            return EventType.BYE;
        }
        if (arr[0].equals("list")) {
            return EventType.LIST;
        }
        if (arr[0].equals("mark")) {
            return EventType.MARK;
        }
        if (arr[0].equals("unmark")) {
            return EventType.UNMARK;
        }
        if (arr[0].equals("todo")) {
            return EventType.TODO;
        }
        if (arr[0].equals("deadline")) {
            return EventType.DEADLINE;
        }
        if (arr[0].equals("event")) {
            return EventType.EVENT;
        }

        return EventType.ADD;
    }

    private static void markEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t = storedInputs.get(num-1);
        t.markDone();
        System.out.println("\nNice! I've marked this task as done:\n  " + t + "\n");
    }

    private static void unmarkEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t = storedInputs.get(num-1);
        t.markUnDone();
        System.out.println("\nOK, I've marked this task as not done yet:\n  " + t + "\n");
    }

    private static void todoEvent(String userInput) {
        Task temp = new ToDo(userInput);
        storedInputs.add(temp);
        System.out.println(temp);
    }

    private static void deadlineEvent(String userInput) {
        Task temp = new Deadline(userInput, userInput);
        storedInputs.add(temp);
        System.out.println(temp);
    }

    private static void eventEvent(String userInput) {
        Task temp = new Event(userInput, userInput, userInput);
        storedInputs.add(temp);
        System.out.println(temp);
    }

    private static String printList() {
        String s = "";
        for (int i = 1; i <= storedInputs.size(); i++) {
            s += i + ". " + storedInputs.get(i-1) + "\n";
        }
        return s;
    }
}
