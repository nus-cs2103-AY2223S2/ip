import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {
        ArrayList<Task> storage = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String spacingIndent = "    ";
        String welcomeMsg = spacingIndent + " "
                + "Hello! I'm Duke\n"
                + spacingIndent + " "
                + "How can I help you?";
        String exitMsg = spacingIndent + " "
                + "Farewell! See you soon!";
        String dashes = spacingIndent
                + "______________________________"
                + "______________________________";
        String curInput = "";
        boolean status = true;
        System.out.println(dashes);
        System.out.println(welcomeMsg);
        System.out.println(dashes);

        while (status) {
            try {
                curInput = sc.nextLine();

                if (curInput.equals("bye")){
                    status = false;
                } else if (curInput.equals("list")) {
                    System.out.println(dashes);
                    System.out.println(spacingIndent
                            + "Here are the tasks in your list:");
                    for (int i = 0; i < storage.size(); i++) {
                        int number = i + 1;
                        System.out.println(spacingIndent + " "
                                + number + ". " + storage.get(i));
                    }
                    System.out.println(dashes);
                } else if (curInput.startsWith("mark")) {
                    int targetIndex = Integer.parseInt(
                            curInput.substring(5)) - 1;
                    storage.set(targetIndex,
                            storage.get(targetIndex).markAsDone());
                    System.out.println(dashes);
                    System.out.println(spacingIndent
                            + "Nice! I've marked this task as done:");
                    System.out.println(spacingIndent
                            + "   " + storage.get(targetIndex));
                    System.out.println(dashes);
                } else if (curInput.startsWith("unmark")) {
                    int targetIndex = Integer.parseInt(
                            curInput.substring(7)) - 1;
                    storage.set(targetIndex,
                            storage.get(targetIndex).markAsDone());
                    System.out.println(dashes);
                    System.out.println(spacingIndent
                            + "OK, I've marked this task as not done yet:");
                    System.out.println(spacingIndent
                            + "   " + storage.get(targetIndex));
                    System.out.println(dashes);
                } else if (curInput.startsWith("todo")) {
                    if (curInput.length() == 4) {
                        throw new DukeException(
                                "The description of a todo cannot be empty.");
                    }
                    Todo taskTodo = new Todo(curInput.substring(5));
                    storage.add(taskTodo);
                    System.out.println(dashes);
                    System.out.println(spacingIndent + " New task added:");
                    System.out.println(spacingIndent + "  " + taskTodo);
                    System.out.println(spacingIndent
                            + " Now list has " + storage.size() + " tasks.");
                    System.out.println(dashes);
                } else if (curInput.startsWith("deadline")) {
                    String desc = curInput.substring(9);
                    String[] descSplit = desc.split("/");
                    Deadline taskDeadline = new Deadline(
                            descSplit[0].substring(0,descSplit[0].length()-1),
                            descSplit[1].substring(3));
                    storage.add(taskDeadline);
                    System.out.println(dashes);
                    System.out.println(spacingIndent + " New task added:");
                    System.out.println(spacingIndent + "  " + taskDeadline);
                    System.out.println(spacingIndent
                            + " Now list has " + storage.size() + " tasks.");
                    System.out.println(dashes);
                } else if (curInput.startsWith("event")) {
                    String desc = curInput.substring(6);
                    String[] descSplit = desc.split("/");
                    String description = descSplit[0].substring(
                            0,descSplit[0].length()-1);
                    String start = descSplit[1].substring(
                            5,descSplit[1].length()-1);
                    String end = descSplit[2].substring(3);
                    Event taskEvent = new Event(description, start, end);
                    storage.add(taskEvent);
                    System.out.println(dashes);
                    System.out.println(spacingIndent + " New task added:");
                    System.out.println(spacingIndent + "  " + taskEvent);
                    System.out.println(spacingIndent
                            + " Now list has " + storage.size() + " tasks.");
                    System.out.println(dashes);
                } else if (curInput.startsWith("delete")) {
                    int targetIndex = Integer.parseInt(
                            curInput.substring(7)) - 1;
                    Task removedTask = storage.get(targetIndex);
                    storage.remove(targetIndex);
                    System.out.println(dashes);
                    System.out.println(spacingIndent
                            + " Task removed:");
                    System.out.println(spacingIndent
                            + "   " + removedTask);
                    System.out.println(spacingIndent
                            + " Now list has " + storage.size() + " tasks.");
                    System.out.println(dashes);
                }
                else {
                    throw new DukeException(
                            "I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeException){
                System.out.println(dashes);
                System.out.println(spacingIndent + " "
                        + dukeException.getMessage());
                System.out.println(dashes);
            }
        }

        System.out.println(dashes);
        System.out.println(exitMsg);
        System.out.println(dashes);

    }
}
