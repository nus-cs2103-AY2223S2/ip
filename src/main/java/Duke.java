import java.time.DateTimeException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {

    // array that contains all the tasks thus far
    private static ArrayList<Task> LISTOFTHINGS = new ArrayList<>();
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * prints out the text with lines on top and below
     * @param text
     */
    public static void printWithLines(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + text);
        System.out.println("    ____________________________________________________________");
    }

    /**
     *
     * @return the string that updates the number of elements left in the string
     */
    public static String listUpdate() {
        String plural = "";
        if (LISTOFTHINGS.size() > 1) {
            plural = "s";
        }
        return "\n     Now you have " + LISTOFTHINGS.size() + " task" + plural + " in the list.";
    }


    /**
     * creates the localdatetime by parsing the text string
     * @param dateTime the string representation of the local date time
     * @return the LocalDateTime object being created
     */
    public static LocalDateTime createLocalDateTime(String dateTime) {
        LocalDateTime date;
        try {
            String stringWithNoTrailingWhitespaces = dateTime.trim();
            date = LocalDateTime.parse(stringWithNoTrailingWhitespaces, FORMATTER);
        } catch (DateTimeException e) {
            date = null;
        }
        return date;
    }



    /**
     *
     * @param text the text containing the information of the command
     * @param add type of add command use
     * @throws DukeException when the format is wrong
     */
    public static void addItem(String text, AddCommands add) throws DukeException {

        Task addedItem = null;
        boolean isValidToAdd = false;
        if (add.equals(AddCommands.TODO)) {
            String contents = text.substring(4);
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }

            addedItem = new Todo(contents);
            isValidToAdd = true;

        } else if (add.equals(AddCommands.DEADLINE)) {
            String contents = text.substring(8);
            String[] arr = contents.split("/by");
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            LocalDateTime end = createLocalDateTime(arr[1]);
            if (end != null) {
                addedItem = new Deadline(arr[0], end);
                isValidToAdd = true;
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        } else {
            String contents = text.substring(5);
            String[] arr1 = contents.split("/from");
            if (arr1.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");
            }
            String[] arr2 = arr1[1].split("/to");
            if (arr2.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'event [do something] /from [start date] /to [end date]'");

            }
            LocalDateTime start = createLocalDateTime(arr2[0]);
            LocalDateTime end = createLocalDateTime(arr2[1]);

            if (start != null && end != null) {
                addedItem = new Event(arr1[0], start, end);
                isValidToAdd = true;
            } else {
                throw new DukeException("Format date as YYYY-MM-DD HH:mm");
            }
        }
        if (isValidToAdd) {
            LISTOFTHINGS.add(addedItem);
            String str = "  " + addedItem.toString();
            str = " Got it. I've added this task:\n     " + str;
            printWithLines(str + listUpdate());
        }
    }



    /**
     * iterates through the arraylist and prints out the elements inside
     */
    public static void printList() {

        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < LISTOFTHINGS.size(); i++) {
            totalString += "\n     " + (i+1) + "." + LISTOFTHINGS.get(i).toString();
        }
        printWithLines(totalString);
    }



    /**
     * removes the item in the list
     * @param index the index of the item to be removed
     */
    public static void removeItem(int index) {
        String str = " Noted. I'm removing this task:\n       " + LISTOFTHINGS.get(index).toString();
        LISTOFTHINGS.remove(index);
        printWithLines(str + listUpdate());

    }


    public static void main(String[] args) {
        printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String line = input.nextLine();
                String upperLine = line.toUpperCase();
                String command = upperLine.split(" ")[0];
                if (StartingCommands.contains(command)) {
                    StartingCommands sc = StartingCommands.valueOf(command);
                    if (sc.equals(StartingCommands.BYE)) {
                        if (upperLine.replaceFirst("BYE", "").equals("")) {
                            break;
                        } else {
                            throw new DukeException("Did you mean to say bye? Type 'bye' to quit the program.");
                        }
                    } else if (sc.equals(StartingCommands.LIST)) {
                        if (upperLine.replaceFirst("LIST", "").equals("")) {
                            printList();
                        } else {
                            throw new DukeException("No argument in list allowed.");
                        }
                    } else if (sc.equals(StartingCommands.MARK)) {


                        String[] arr = upperLine.split(" ");
                        if (arr.length != 2) {
                            throw new DukeException("Wrong format. Format it as 'mark [index]'");
                        }
                        if (!arr[1].chars().allMatch(Character::isDigit)) {
                            throw new DukeException("Index should be a number");
                        }
                        int idx = Integer.parseInt(arr[1]) - 1;
                        if (idx >= LISTOFTHINGS.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        Task thisTask = LISTOFTHINGS.get(idx);
                        thisTask.markDone();
                    } else if (sc.equals(StartingCommands.UNMARK)) {
                        String[] arr = upperLine.split(" ");
                        if (arr.length != 2) {
                            throw new DukeException("Wrong format. Format it as 'mark [index]'");
                        }
                        if (!arr[1].chars().allMatch(Character::isDigit)) {
                            throw new DukeException("Index should be a number");
                        }
                        int idx = Integer.parseInt(arr[1]) - 1;
                        if (idx >= LISTOFTHINGS.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        Task thisTask = LISTOFTHINGS.get(idx);
                        thisTask.markUndone();

                    } else if (sc.equals(StartingCommands.DELETE)) {
                        String[] arr = upperLine.split(" ");
                        if (arr.length != 2) {
                            throw new DukeException("Only one argument for delete allowed");
                        }
                        String idxStr = arr[1];
                        if (!idxStr.chars().allMatch(Character::isDigit)) {
                            throw new DukeException("Argument must be a digit");
                        }
                        int idx = Integer.parseInt(idxStr) - 1;
                        if (idx >= LISTOFTHINGS.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        removeItem(idx);
                    }
                }
                else {
                    if (AddCommands.contains(command)) {
                        addItem(line, AddCommands.valueOf(command));
                    } else {
                        throw new DukeException("I'm sorry. I don't know what that means.");
                    }
                }
            } catch (DukeException e) {
                printWithLines(" " + e.toString());
            }
        }
        printWithLines(" Bye! Hope to see you again soon!");
    }
}

