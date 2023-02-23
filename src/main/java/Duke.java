import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    /**
     * Minimum length of a string command is given by
     * The length of the command +2 (for whitespace and
     * at least 1 letter for the command)
     */
    static final String HOMEDIRECTORY = System.getProperty("user.dir");
    static final Path DUKELISTDIRECTORY = Paths.get(HOMEDIRECTORY, "SavedList.txt");
    static final HashMap<String, Integer> MINVALIDLENGTH = new HashMap<>(Map.of(
            "todo", 6,
            "deadline", 10,
            "event", 7,
            "mark", 6,
            "unmark", 8,
            "delete", 8
    ));
    /**
     * Correct formatting of commands given that the name of the command is correct
     */
    static final HashMap<String, String> CORRECTFORMAT = new HashMap<>(Map.of(
            "todo", "todo THE TASK",
            "deadline", "deadline THE TASK /by TIME",
            "event", "event THE TASK /from TIME /to TIME",
            "mark", "mark NUMBER",
            "unmark", "unmark NUMBER",
            "delete", "delete NUMBER"
    ));
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        // arr => needs to be updated w old list
        ArrayList<Task> arr = new ArrayList<>();
        try {
            arr = readSavedFile();
        } catch (IOException unknown) {
            print(unknown + "\nUnsure of error");
        }

        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit();
                break;
            }
            execute(cmd, arr);
//            echo(sc.nextLine());
        }
        sc.close();
    }
    public static ArrayList<Task> readSavedFile() throws IOException {
        try {
            Files.createFile(DUKELISTDIRECTORY);
            return new ArrayList<Task>();
        } catch (FileAlreadyExistsException e) {
            ArrayList<Task> arr = new ArrayList<>();
            List<String> savedList = Files.readAllLines(DUKELISTDIRECTORY);
            for (String task : savedList) {
                try {
                    arr.add(readLineToTask(task));
                } catch (WrongTaskFormatException | ArrayIndexOutOfBoundsException wrongFormat) {
                    print("File format of tasks is wrong.\n" +
                            "List now contains information up to line before wrongly formatted line.");
                    printList(arr);
                    return arr;
                }
            }
            return arr;
        }
    }
    public static Task readLineToTask(String taskDescription) throws WrongTaskFormatException {
        String[] items = taskDescription.split(" \\| ");
        boolean isDone = getIsDone(items[1]);
        Task task = new Task(taskDescription, isDone);
        switch(items[0]) {
            case "T":
                task = new ToDo(items[2], isDone);
//                return task;
                break;
            case "D":
                task = new Deadline(items[2], items[3], isDone);
//                return task;
                break;
            case "E":
                String[] timeOfEvent = items[3].split("-");
                task = new Event(items[2], timeOfEvent[0], timeOfEvent[1], isDone);
//                return task;
                break;
            default:
                throw new WrongTaskFormatException("Invalid Task String Format");
        }
        return task;
    }

    /**
     * @param doneString a simple string containing 0/1
     * @returns the int value
     */
    public static boolean getIsDone(String doneString) {
        return doneString.equals("1");
    }
    static void execute(String cmd, ArrayList<Task> arr) {
        String[] words = cmd.split(" ");
        switch (words[0]) {
            case "list":
                printList(arr);
                break;
            case "mark":
                print(mark(words[1], arr));
                break;
            case "unmark":
                print(unmark(words[1], arr));
                break;
            case "delete":
                print(delete(words[1], arr));
                break;
            default:    // for tasks
                print(add(cmd, arr));
        }
        saveList(arr);
    }
    static void saveList(ArrayList<Task> arr) {
        try {
            // no need to deleteIfExists as BufferedWritter automatically clears all prev input
            BufferedWriter fileWriter = Files.newBufferedWriter(DUKELISTDIRECTORY);
            for (Task task : arr) {
                fileWriter.write(task.saveString());
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            // unsure of what cases would throw IOException under deleteIfExists
        }
    }
    static String add(String cmd, ArrayList<Task> arr) {
        String[] words = cmd.split(" ");
        String typeOfTask = words[0];

        if (!MINVALIDLENGTH.containsKey(typeOfTask)) {
            return errorMsg("I'm sorry, but I don't know what that means");
        } else if (cmd.length() < MINVALIDLENGTH.get(typeOfTask)) {
            return errorMsg(String.format(
                    "The description of a %s cannot be empty.", typeOfTask));
        }

        try {
            Task task = makeTask(typeOfTask, cmd);
            //.replace(typeOfTask + " ", ""));
            arr.add(task);
            return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.", task, arr.size());
        } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException wrongFormat) {
            return errorMsg("Please enter the command in the correct format.") + "\n" + CORRECTFORMAT.get(typeOfTask);
        }
    }
    static Task makeTask(String name, String cmd) throws PatternSyntaxException {
        Task task = new Task(cmd);
        cmd = cmd.replace(name + " ", "");
        switch (name) {
            case "todo":
                task = new ToDo(cmd);
                break;
            case "deadline":
                task = new Deadline(cmd);
                break;
            case "event":
                task = new Event(cmd);
                break;
        };
        return task;
    }
    static String mark(String num, ArrayList<Task> arr) {     //try catch, possibility of error if user enter wrong cmd
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            arr.get(index).markAsDone();
            return String.format("Nice! I've marked this task as done: \n\t%s", arr.get(index));
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
    static String unmark(String num, ArrayList<Task> arr) {   //try catch, possibility of error if user enter wrong cmd
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            arr.get(index).unmarkAsDone();
            return String.format("Ok, I've marked this task as not done yet: \n\t%s", arr.get(index));
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
    static String delete(String num, ArrayList<Task> arr) {
        if (arr.size() == 0) {
            return errorMsg("You do not have any items in your list!");
        }
        try {
            int index = Integer.parseInt(num) - 1;
            String taskDescription = arr.get(index).toString();
            arr.remove(index);
            return String.format("Noted, I've removed this task: \n\t%s\nNow you have %d tasks in this list.",
                    taskDescription, arr.size());
        } catch (NumberFormatException notANumber) {
            return errorMsg("Please enter a valid number");
        } catch (IndexOutOfBoundsException badNumber) {
            return errorMsg(String.format(
                    "Please enter a number from 1 to %d",
                    arr.size()));
        }
    }
    static String ownName() {
        String name = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return name;
    }
    static String greet() {
        return String.format("Hello I am:\n%sWhat can I do for you?", ownName());
    }
    static void printList(ArrayList<Task> arr) {
        String str = "List:";
        for (int i = 0; i < arr.size(); i++) {
            str += String.format("\n\t%d. %s", i+1, arr.get(i));
        }
        print(str);
    }
    static void exit() {
        print("Bye. Hope to see you again soon!");
    }
    /**
     * Used to print out any reply with the correct formatting
     */
    static void print(String reply) {
        String topBottom = "~~~~~~~~~~~~~~~~~~~~";
        System.out.println(String.format("%s\n%s\n%s", topBottom, reply, topBottom));
    }
    static String errorMsg(String error) {
        return String.format("☹ OOPS!!! %s :-(", error);
    }
    /**
     * Used in Level-1 to echo
     */
    static void echo(String cmd) {
        System.out.println(cmd);
    }
}
