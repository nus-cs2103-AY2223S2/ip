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
    public static void addItem(String text) throws DukeException {

        Task addedItem = null;
        if (text.startsWith("todo")) {
            String contents = text.replace("todo", "");
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            addedItem = new Todo(contents);

        } else if (text.startsWith("deadline")) {
            String contents = text.replace("deadline", "");
            String[] arr = contents.split("/by");
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            addedItem = new Deadline(arr[0], arr[1]);
        } else if (text.startsWith("event")){
            String contents = text.replace("event", "");
            String[] arr1 = contents.split("/from");
            if (arr1.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
            }
            String[] arr2 = arr1[1].split("/to");
            if (arr2.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");

            }
            addedItem = new Event(arr1[0], arr2[0], arr2[1]);
        } else {
            throw new DukeException("I'm sorry, I don't know what that means :(");
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
            try {
                String line = input.nextLine();
                String lowerLine = line.toLowerCase();
                if (lowerLine.startsWith("bye")) {
                    if (lowerLine.replace("bye", "").equals("")) {
                        break;
                    } else {
                        throw new DukeException("Did you mean to say bye? Type 'bye' to quit the program.");
                    }
                } else if (lowerLine.startsWith("list")) {
                    if (lowerLine.replace("list", "").equals("")) {
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
                } else {
                    addItem(line);
                }
            } catch (DukeException e) {
                printWithLines(" " + e.toString());
            }
        }
        printWithLines("Bye! Hope to see you again soon!");
    }
}

