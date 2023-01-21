import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;

public class Duke {



    // array that contains all the tasks thus far
    private static ArrayList<Task> listOfThings = new ArrayList<>();
    private static File f = null;
    private static final String dirName = "." + File.separator + "src" + File.separator + "main" + File.separator + "data";
    private static final String pathName = dirName + File.separator + "duke";


    /**
     * prints out the text with lines on top and below
     * @param text the content that is to be printed out
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
        if (listOfThings.size() > 1) {
            plural = "s";
        }
        return "\n       Now you have " + listOfThings.size() + " task" + plural + " in the list.";
    }


    /**
     * the function that formats the task to store into the duke file
     * @param t the task to be appended to the file
     * @return the string representation to be appended to the file
     */
    public static String taskStringFormatter(Task t) {
        String s = "";
        if (t instanceof Todo) {
            Todo todo = (Todo) t;
            s = todo.statusStringForFile();
        } else if (t instanceof Deadline) {
            Deadline deadline = (Deadline) t;
            s = deadline.statusStringForFile();
        } else {
            Event event = (Event) t;
            s = event.statusStringForFile();
        }
        return s;
    }



    /**
     *
     * @param text the text containing the information of the command
     * @param add type of add command use
     * @throws DukeException when the format is wrong
     */
    public static void addItem(String text, AddCommands add) throws DukeException {
        Task addedItem = null;
        if (add.equals(AddCommands.TODO)) {
            String contents = text.substring(4);
            if (contents.length() == 0) {
                throw new DukeException("The description of a todo cannot be empty");
            }
            addedItem = new Todo(contents, false);
        } else if (add.equals(AddCommands.DEADLINE)) {
            String contents = text.substring(8);
            String[] arr = contents.split("/by");
            if (arr.length != 2) {
                throw new DukeException("I don't know what that means. Format it as 'deadline [do something] /by [date]");
            }
            addedItem = new Deadline(arr[0], false, arr[1]);
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
            addedItem = new Event(arr1[0], false, arr2[0], arr2[1]);
        }
        listOfThings.add(addedItem);
        writeToFile(addedItem);
        String str = "  " + addedItem.toString();
        str = " Got it. I've added this task:\n     " + str;
        printWithLines(str + listUpdate());
    }


    /**
     * writes the tasks to the duke file
     * @param item the task that is inserted into the duke file
     */
    public static void writeToFile(Task item) {
        try {
            String fileInputString = taskStringFormatter(item);
            FileWriter fw = new FileWriter(pathName, true);
            fw.write(fileInputString + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * iterates through the arraylist and prints out the elements inside
     */
    public static void printList() {
        String totalString = "";
        totalString += " Here are the tasks in your list:";
        for (int i = 0; i < listOfThings.size(); i++) {
            totalString += "\n     " + (i+1) + "." + listOfThings.get(i).toString();
        }
        printWithLines(totalString);
    }



    /**
     * removes the item in the list
     * @param index the index of the item to be removed
     */
    public static void removeItem(int index) {
        String str = " Noted. I'm removing this task:\n       " + listOfThings.get(index).toString();
        listOfThings.remove(index);
        printWithLines(str + listUpdate());
    }



    /**
     * loads all the items from the duke file
     */
    public static void loadFromFile() {
        try {
            Files.createDirectories(Paths.get(dirName));
            if (f == null) {
                f = new File(pathName);
            }
            f.createNewFile();
            Scanner sc = new Scanner(f);

            while (sc.hasNext()) {
                String str = sc.nextLine();
                String[] valueArr = str.split("/");
                // throw exceptions here later if you want

                String type = valueArr[0].toUpperCase().trim();
                AddCommands commandType = AddCommands.valueOf(type);
                Task thisTask = null;
                boolean doneOrNot = valueArr[1].equals("1");
                if (commandType.equals(AddCommands.TODO)) {
                    thisTask = new Todo(valueArr[2], doneOrNot);
                } else if (commandType.equals(AddCommands.DEADLINE)) {
                    thisTask = new Deadline(valueArr[2], doneOrNot, valueArr[3]);
                } else {
                    thisTask = new Event(valueArr[2], doneOrNot, valueArr[3], valueArr[4]);
                }
                listOfThings.add(thisTask);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * the function that deletes the entire duke file and re-inserts it with the current one.
     * used for delete, mark and unmark when values are changed
     */
    public static void deleteFileAndRedo() {
        if (f.delete()) {
            for (Task item : listOfThings) {
                writeToFile(item);
            }
        } else {
            System.out.println("File deletion failed");
        }
    }



    public static void main(String[] args) {
        printWithLines(" Hello! I'm Duke!\n     What can I do for you today?");
        loadFromFile();
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                String line = input.nextLine();
                String upperLine = line.toUpperCase();
                String command = upperLine.split(" ")[0];
                if (StartingCommands.contains(command)) {
                    StartingCommands sc = StartingCommands.valueOf(command);
                    if (sc.equals(StartingCommands.BYE)) {
                        if (upperLine.trim().equals("BYE")) {
                            break;
                        } else {
                            throw new DukeException("Did you mean to say bye? Type 'bye' to quit the program.");
                        }
                    } else if (sc.equals(StartingCommands.LIST)) {
                        if (upperLine.trim().equals("LIST")) {
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
                        if (idx >= listOfThings.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        Task thisTask = listOfThings.get(idx);
                        thisTask.markDone();
                        deleteFileAndRedo();
                    } else if (sc.equals(StartingCommands.UNMARK)) {
                        String[] arr = upperLine.split(" ");
                        if (arr.length != 2) {
                            throw new DukeException("Wrong format. Format it as 'mark [index]'");
                        }
                        if (!arr[1].chars().allMatch(Character::isDigit)) {
                            throw new DukeException("Index should be a number");
                        }
                        int idx = Integer.parseInt(arr[1]) - 1;
                        if (idx >= listOfThings.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        Task thisTask = listOfThings.get(idx);
                        thisTask.markUndone();
                        deleteFileAndRedo();
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
                        if (idx >= listOfThings.size() || idx < 0) {
                            throw new DukeException("This index doesn't exist.");
                        }
                        removeItem(idx);
                        deleteFileAndRedo();
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

