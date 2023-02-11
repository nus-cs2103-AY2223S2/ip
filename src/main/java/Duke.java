import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public class Duke {
    /**
     * Minimum length of a string command is given by
     * The length of the command +2 (for whitespace and
     * at least 1 letter for the command)
     */
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

        ArrayList<Task> arr = new ArrayList<>();
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
