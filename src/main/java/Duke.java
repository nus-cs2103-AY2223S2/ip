import java.io.*;
import java.util.*;

public class Duke {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Task> db = new ArrayList<Task>(100);
    
    protected static String logoString = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
    protected static String outlinesString = "____________________________________________________________";
    protected static String introductionString = "Hello! I'm Duke\nWhat can I do for you?";
    protected static String farewellString = "Bye. Hope to see you again soon!";
    protected static String readListString = "Here are the tasks in your list:";

    public static void main(String[] args) throws IOException {
        System.out.println(outlinesString + "\n" + introductionString + "\n" + outlinesString);
        boolean continueConvo = true;

        while (continueConvo) {
            String input = br.readLine();
            System.out.println(outlinesString);
            try {
                continueConvo = handleMessage(input);
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } 
            System.out.println(outlinesString + "\n");
        }
        br.close();
    }

    private static boolean endConvo() {
        System.out.println(farewellString);
        return false;
    }

    private static boolean handleMessage(String message) {
        if (message.equals("list")) { read(); }
        else if (message.equals("bye")) { return endConvo(); }
        else if (message.startsWith("mark")) { markTask(message); }
        else if (message.startsWith("unmark", 0)) { unmarkTask(message); }
        else if (message.startsWith("todo")) { updateToDo(message); }
        else if (message.startsWith("deadline")) { updateDeadline(message); }
        else if (message.startsWith("event")) { updateEvent(message); }
        else { throw new InvalidInputException(null); }
        return true;
    }
    private static void read() {
        if (db.isEmpty()) { throw new NoTaskException(null); }
        System.out.println(readListString);
        for (int i = 1; i <= db.size(); i++) {
            System.out.println(i + "." + db.get(i-1));
        }
    }

    private static void update(Task task) {
        db.add(task);
        System.out.println("Now you have " + db.size() + " tasks in the list");
    }

    private static void markTask(String message) {
        int ind = Integer.parseInt(message.split(" ")[1]) - 1;
        db.get(ind).setDone();
    }

    private static void unmarkTask(String message) {
        int ind = Integer.parseInt(message.split(" ")[1]) - 1;
        db.get(ind).setUndone();
    }

    private static void updateToDo(String message) {
        try {
            ToDo toDo = new ToDo(message.split("todo ")[1]);
            System.out.println(Task.addTaskString + "\n" + toDo);
            update(toDo);
        } catch (ArrayIndexOutOfBoundsException e) { throw new NoDescriptionException(message, e); }
    }

    private static void updateDeadline(String message) {
        try {
        String[] temp = message.split("deadline ");
        temp = temp[1].split(" /by ");
        Deadline deadline = new Deadline(temp[0], temp[1]);
        System.out.println(Task.addTaskString + "\n" + deadline);
        update(deadline);
        } catch (ArrayIndexOutOfBoundsException e) { throw new NoDescriptionException(message, e); }
    }

    private static void updateEvent(String message) {
        try {
        String[] temp = message.split("event ");
        temp = temp[1].split(" /from ");
        String description = temp[0];
        temp = temp[1].split(" /to ");
        Event event = new Event(description, temp[0], temp[1]); 
        System.out.println(Task.addTaskString + "\n" + event);
        update(event);
        } catch (ArrayIndexOutOfBoundsException e) { throw new NoDescriptionException(message, e); }
    }
}
