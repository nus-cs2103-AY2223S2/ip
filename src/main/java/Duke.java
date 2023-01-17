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

    public static String listUpdate() {
        String plural = "";
        if (listOfThings.size() > 1) {
            plural = "s";
        }
        return "\n       Now you have " + listOfThings.size() + " task" + plural + " in the list.";
    }

    // adding item to the list of things, as well as printing the task that is added
    public static void addItem(String text, String lowerText) throws DukeException {

        Task addedItem = null;
        if (lowerText.startsWith("todo")) {
            String contents = text.substring(4);
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            addedItem = new Todo(contents);

        } else if (lowerText.startsWith("deadline")) {
            String contents = text.substring(8);
//                    text.replaceFirst("deadline", "");
            String[] arr = contents.split("/by");
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            addedItem = new Deadline(arr[0], arr[1]);
        } else {
            String contents = text.substring(5);
//                    text.replaceFirst("event", "");
            String[] arr1 = contents.split("/from");
            if (arr1.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
            }
            String[] arr2 = arr1[1].split("/to");
            if (arr2.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");

            }
            addedItem = new Event(arr1[0], arr2[0], arr2[1]);
        }
        listOfThings.add(addedItem);
        String str = "  " + addedItem.toString();
        str = " Got it. I've added this task:\n     " + str;
        printWithLines(str + listUpdate());
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

    // removing the item
    public static void removeItem(int index) {
        String str = " Noted. I'm removing this task:\n       " + listOfThings.get(index).toString();
        listOfThings.remove(index);
        printWithLines(str + listUpdate());

    }

    // main driver function
    public static void main(String[] args) {
        printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String line = input.nextLine();
                String lowerLine = line.toLowerCase();
                if (lowerLine.startsWith("bye")) {
                    if (lowerLine.replaceFirst("bye", "").equals("")) {
                        break;
                    } else {
                        throw new DukeException("Did you mean to say bye? Type 'bye' to quit the program.");
                    }
                } else if (lowerLine.startsWith("list")) {
                    if (lowerLine.replaceFirst("list", "").equals("")) {
                        printList();
                    } else {
                        throw new DukeException("No argument in list allowed.");
                    }
                } else if (lowerLine.startsWith("mark") || lowerLine.startsWith("unmark")) {
                    String[] arr = lowerLine.split(" ");
                    if (arr.length != 2) {
                        throw new DukeException("Wrong format. Format it as 'mark [index]' or 'unmark [index]'");
                    }
                    if (!arr[1].chars().allMatch(Character::isDigit)) {
                        throw new DukeException("Index should be a number");
                    }
                    int idx = Integer.parseInt(arr[1]) - 1;
                    String command = arr[0];
                    if (idx >= listOfThings.size() || idx < 0) {
                        throw new DukeException("This index doesn't exist.");
                    }
                    Task thisTask = listOfThings.get(idx);
                    if (command.equals("mark")) {
                        thisTask.markDone();
                    } else {
                        thisTask.markUndone();
                    }
                } else if (lowerLine.startsWith("delete")) {
                    String[] arr = lowerLine.split(" ");
                    if (arr.length != 2) {
                        throw new DukeException("Only one argument for delete allowed");
                    }
                    String idxStr = arr[1];
                    if (!idxStr.chars().allMatch(Character::isDigit)) {
                        throw new DukeException("Argument must be a digit");
                    }
                    int idx = Integer.parseInt(idxStr) - 1;
                    if (idx >= listOfThings.size() || idx < 0) {
                        throw new DukeException("This index doesn't exist.");
                    }
                    removeItem(idx);
                } else if (lowerLine.startsWith("todo") || lowerLine.startsWith("deadline") || lowerLine.startsWith("event")) {
                    addItem(line, lowerLine);
                } else {
                throw new DukeException("I'm sorry. I don't know what that means.");
                }
            } catch (DukeException e) {
                printWithLines(" " + e.toString());
            }
        }
        printWithLines(" Bye! Hope to see you again soon!");
    }
}

